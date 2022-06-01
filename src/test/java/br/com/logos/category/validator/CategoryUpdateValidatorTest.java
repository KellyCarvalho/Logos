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
        CategoryUpdateDTO dto = CategoryUpdateDTO.builder().id(2L).code("programacao").build();
        dtoValidator.validate(dto, errors);

        verify(errors).rejectValue("code","category.code.already.exists");
    }

    @Test
    void validateShouldNotShowErrorIfCodeAlreadyExistsAndIdCategoryIsEqualToCurrentCategoryId(){
        CategoryUpdateDTO dto = CategoryUpdateDTO.builder().id(1L).code("programacao").build();
        dtoValidator.validate(dto, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void validateShouldNotErrorIfCodeDoNotExistsAndIdDoNotExists(){
        CategoryUpdateDTO dto = CategoryUpdateDTO.builder().id(2L).code("business").build();
        dtoValidator.validate(dto, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void validateShouldNotErrorIfCodeDoNotExistsAndIdIsEqualCurrentId(){
        CategoryUpdateDTO dto = CategoryUpdateDTO.builder().id(1L).code("business").build();
        dtoValidator.validate(dto, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}
