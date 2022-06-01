package br.com.logos.course.validator;

import br.com.logos.course.CourseInsertDTO;
import br.com.logos.course.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class CourseInsertValidator implements Validator {

   private final CourseRepository courseRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return CourseInsertDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CourseInsertDTO dto = (CourseInsertDTO) target;
        if (courseRepository.existsByCode(dto.getCode())){
            errors.rejectValue("code", "course.code.already.exists");
        }
    }
}
