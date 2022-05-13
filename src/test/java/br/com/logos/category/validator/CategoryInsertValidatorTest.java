package br.com.logos.category.validator;


import br.com.logos.category.CategoryInsertDTO;
import br.com.logos.category.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.*;

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
   void validateShouldShowErrorIfCodeAlreadyExists(){
        when(repository.existsByCode("programacao")).thenReturn(true);

        CategoryInsertDTO dto = CategoryInsertDTO.builder().code("programacao").build();
        dtoValidator.validate(dto, errors);

        verify(errors).rejectValue("code", "category.code.already.exists");
    }

    @Test
    void validateShouldNotShowErrorIfCodeDoesNotExist(){
        CategoryInsertDTO dto = CategoryInsertDTO.builder().code("programacao").build();
        dtoValidator.validate(dto, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}
