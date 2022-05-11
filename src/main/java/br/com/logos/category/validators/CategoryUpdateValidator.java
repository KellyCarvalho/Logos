package br.com.logos.category.validators;

import br.com.logos.category.CategoryRepository;
import br.com.logos.category.CategoryUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
//TODO Lombok usar o required
@Component
public class CategoryUpdateValidator implements Validator {

    private final CategoryRepository categoryRepository;

    public CategoryUpdateValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CategoryUpdateDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CategoryUpdateDTO dto = (CategoryUpdateDTO) target;
        if (categoryRepository.existsByCodeAndIdNot(dto.getCode(), dto.getId())) {
            errors.rejectValue("code", "category.code.already.exists");
        }
    }
}
