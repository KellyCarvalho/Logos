package br.com.logos.course.validator;


import br.com.logos.course.CourseInsertDTO;
import br.com.logos.course.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.*;

public class CourseInsertValidatorTest {

    private CourseRepository repository;
    private CourseInsertValidator dtoValidator;
    private Errors errors;

    @BeforeEach
    void setUp(){
        repository = mock(CourseRepository.class);
        dtoValidator = new CourseInsertValidator(repository);
        errors = mock(Errors.class);
    }

    @Test
   void validateShouldShowErrorIfCodeAlreadyExists(){
        when(repository.existsByCode("java")).thenReturn(true);

        var dto = new CourseInsertDTO();
        dto.setCode("java");

        dtoValidator.validate(dto, errors);

        verify(errors).rejectValue("code", "course.code.already.exists");
    }

    @Test
    void validateShouldNotShowErrorifCodeDoesNotExist(){
        var dto = new CourseInsertDTO();
        dto.setCode("java");

        dtoValidator.validate(dto, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}
