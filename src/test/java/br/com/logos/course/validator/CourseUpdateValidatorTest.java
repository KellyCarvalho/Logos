package br.com.logos.course.validator;

import br.com.logos.course.CourseRepository;
import br.com.logos.course.CourseUpdateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.Mockito.*;

public class CourseUpdateValidatorTest {

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
    void validateShouldShowErrorIfCodeAlreadyExistsWithAnotherId(){
        CourseUpdateDTO dto = CourseUpdateDTO.builder().id(2L).code("java").build();
        dtoValidator.validate(dto, errors);

        verify(errors).rejectValue("code","course.code.already.exists");
    }

    @Test
    void validateShouldNotShowErrorIfCodeAlreadyExistsAndIdCourseIsEqualToCurrentCourseId(){
        CourseUpdateDTO dto = CourseUpdateDTO.builder().id(1L).code("java").build();
        dtoValidator.validate(dto, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void validateShouldNotErrorIfCodeDoNotExistsAndIdDoNotExists(){
        CourseUpdateDTO dto = CourseUpdateDTO.builder().id(2L).code("php").build();
        dtoValidator.validate(dto, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void validateShouldNotErrorIfCodeDoNotExistsAndIdIsEqualCurrentId(){
        CourseUpdateDTO dto = CourseUpdateDTO.builder().id(1L).code("php").build();
        dtoValidator.validate(dto, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}
