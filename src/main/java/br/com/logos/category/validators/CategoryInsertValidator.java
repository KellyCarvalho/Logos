package br.com.logos.category.validators;


import br.com.logos.category.CategoryInsertDTO;
import br.com.logos.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CategoryInsertValidator implements Validator {

    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryInsertValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CategoryInsertDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CategoryInsertDTO dto = (CategoryInsertDTO) target;
        if (categoryRepository.existsByCode(dto.getCode())){
            errors.rejectValue("code", "category.code.already.exists");
        }
    }
}
