package br.com.logos.course.validators;

import br.com.logos.course.CourseRepository;
import br.com.logos.course.CourseUpdateDTO;
import br.com.logos.subCategory.SubCategoryRepository;
import br.com.logos.subCategory.SubCategoryUpdateDTO;
import br.com.logos.subCategory.validators.SubCategoryUpdateValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.Mockito.*;

public class SubCategoryUpdateValidatorTest {

    private CourseRepository repository;
    private CourseUpdateValidator dtoValidator;
    private Errors errors;

    @BeforeEach
    void setUp() {
        repository = mock(CourseRepository.class);
        dtoValidator = new CourseUpdateValidator(repository);
        errors = mock(Errors.class);
        when(repository.existsByCodeAndIdNot(eq("java"), not(eq(1L)))).thenReturn(true);
    }

    @Test
    void ifCodeAlreadyExistsWithAnotherIdShouldShowError(){
        var dto = new CourseUpdateDTO();
        dto.setId(2L);
        dto.setCode("java");

        dtoValidator.validate(dto, errors);

        verify(errors).rejectValue("code","course.code.already.exists");
    }

    @Test
    void doesNotShowErrorIfCodeAlreadyExistsAndIdCategoryIsEqualToCurrentCategoryId(){
        var dto = new CourseUpdateDTO();
        dto.setId(1L);
        dto.setCode("java");

        dtoValidator.validate(dto, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}
