package br.com.logos.course.validator;

import br.com.logos.course.CourseRepository;
import br.com.logos.course.CourseUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class CourseUpdateValidator implements Validator {

    private final CourseRepository courseRepository;

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
