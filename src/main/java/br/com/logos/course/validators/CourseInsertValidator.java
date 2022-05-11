package br.com.logos.course.validators;

import br.com.logos.course.CourseInsertDTO;
import br.com.logos.course.CourseRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

//TODO mesmos todo de CategoryValidators
@Component
public class CourseInsertValidator implements Validator {

   private final CourseRepository courseRepository;

  public CourseInsertValidator(CourseRepository courseRepository){
      this.courseRepository = courseRepository;
  }

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
