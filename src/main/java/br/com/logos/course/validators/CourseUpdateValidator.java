package br.com.logos.course.validators;

import br.com.logos.course.CourseRepository;
import br.com.logos.course.CourseUpdateDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

//TODO mesmos todo de CategoryValidators
@Component
public class CourseUpdateValidator implements Validator {

    private CourseRepository courseRepository;

    public CourseUpdateValidator(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CourseUpdateDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CourseUpdateDTO dto = (CourseUpdateDTO) target;
        if (courseRepository.existsByCodeAndIdNot(dto.getCode(), dto.getId())) {
            errors.rejectValue("code", "course.code.already.exists");
        }
    }
}
