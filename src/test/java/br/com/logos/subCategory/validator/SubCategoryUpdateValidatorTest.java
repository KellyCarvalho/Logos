package br.com.logos.subCategory.validator;

import br.com.logos.subCategory.SubCategoryRepository;
import br.com.logos.subCategory.SubCategoryUpdateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.Mockito.*;

public class SubCategoryUpdateValidatorTest {

    private SubCategoryRepository repository;
    private SubCategoryUpdateValidator dtoValidator;
    private Errors errors;

    @BeforeEach
    void setUp() {
        repository = mock(SubCategoryRepository.class);
        dtoValidator = new SubCategoryUpdateValidator(repository);
        errors = mock(Errors.class);
        when(repository.existsByCodeAndIdNot(eq("java"), not(eq(1L)))).thenReturn(true);
    }

    @Test
    void validateShouldShowErrorIfCodeAlreadyExistsWithAnotherId(){
        var dto = new SubCategoryUpdateDTO();
        dto.setId(2L);
        dto.setCode("java");

        dtoValidator.validate(dto, errors);

        verify(errors).rejectValue("code","subCategory.code.already.exists");
    }

    @Test
    void validateShouldNotShowErrorIfCodeAlreadyExistsAndIdSubCategoryIsEqualToCurrentSubCategoryId(){
        var dto = new SubCategoryUpdateDTO();
        dto.setId(1L);
        dto.setCode("java");

        dtoValidator.validate(dto, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void validateShouldNotErrorIfCodeDoNotExistsAndIdDoNotExists(){
        var dto = new SubCategoryUpdateDTO();
        dto.setId(2L);
        dto.setCode("php");

        dtoValidator.validate(dto, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void validateShouldNotErrorIfCodeDoNotExistsAndIdIsEqualCurrentId(){
        var dto = new SubCategoryUpdateDTO();
        dto.setId(1L);
        dto.setCode("php");

        dtoValidator.validate(dto, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}
