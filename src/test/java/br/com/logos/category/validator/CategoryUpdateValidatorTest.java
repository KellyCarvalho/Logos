package br.com.logos.category.validator;

import br.com.logos.category.CategoryRepository;
import br.com.logos.category.CategoryUpdateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.Mockito.*;

public class CategoryUpdateValidatorTest {

    private CategoryRepository repository;
    private CategoryUpdateValidator dtoValidator;
    private Errors errors;

    @BeforeEach
    void setUp() {
        repository = mock(CategoryRepository.class);
        dtoValidator = new CategoryUpdateValidator(repository);
        errors = mock(Errors.class);
        when(repository.existsByCodeAndIdNot(eq("programacao"), not(eq(1L)))).thenReturn(true);
    }

    @Test
    void validateShouldShowErrorIfCodeAlreadyExistsWithAnotherId(){
        var dto = new CategoryUpdateDTO();
        dto.setId(2L);
        dto.setCode("programacao");

        dtoValidator.validate(dto, errors);

        verify(errors).rejectValue("code","category.code.already.exists");
    }

    @Test
    void validateShouldNotShowErrorIfCodeAlreadyExistsAndIdCategoryIsEqualToCurrentCategoryId(){
        var dto = new CategoryUpdateDTO();
        dto.setId(1L);
        dto.setCode("programacao");

        dtoValidator.validate(dto, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void validateShouldNotErrorIfCodeDoNotExistsAndIdDoNotExists(){
        var dto = new CategoryUpdateDTO();
        dto.setId(2L);
        dto.setCode("business");

        dtoValidator.validate(dto, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void validateShouldNotErrorIfCodeDoNotExistsAndIdIsEqualCurrentId(){
        var dto = new CategoryUpdateDTO();
        dto.setId(1L);
        dto.setCode("business");

        dtoValidator.validate(dto, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}
