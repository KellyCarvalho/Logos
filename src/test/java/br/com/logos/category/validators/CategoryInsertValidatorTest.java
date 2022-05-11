package br.com.logos.category.validators;


import br.com.logos.category.CategoryInsertDTO;
import br.com.logos.category.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.*;

//TODO mudar o nome do pacote pra singular
//TODo colocar o nome do método na frente dos testes
//TODo geral mudar pra Should Not
public class CategoryInsertValidatorTest {

    private CategoryRepository repository;
    private CategoryInsertValidator dtoValidator;
    private Errors errors;

    @BeforeEach
    void setUp(){
        repository = mock(CategoryRepository.class);
        dtoValidator = new CategoryInsertValidator(repository);
        errors = mock(Errors.class);
    }

    @Test
   void ifCodeAlreadyExistsShouldShowError(){
        when(repository.existsByCode("programacao")).thenReturn(true);

        var dto = new CategoryInsertDTO();
        dto.setCode("programacao");

        dtoValidator.validate(dto, errors);

        verify(errors).rejectValue("code", "category.code.already.exists");
    }

    @Test
    void ifCodeDoesNotExistShouldNotShowError(){
        var dto = new CategoryInsertDTO();
        dto.setCode("programacao");

        dtoValidator.validate(dto, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}
