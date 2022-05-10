package br.com.logos.subCategory.validators;

import br.com.logos.category.CategoryUpdateDTO;
import br.com.logos.subCategory.SubCategoryInsertDTO;
import br.com.logos.subCategory.SubCategoryRepository;
import br.com.logos.subCategory.SubCategoryUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SubCategoryUpdateValidator implements Validator {

    @Autowired
    private final SubCategoryRepository subCategoryRepository;

    public SubCategoryUpdateValidator(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SubCategoryUpdateDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SubCategoryUpdateDTO dto = (SubCategoryUpdateDTO) target;
        if (subCategoryRepository.existsByCodeAndIdNot(dto.getCode(), dto.getId())) {
            errors.rejectValue("code", "subCategory.code.already.exists");
        }
    }
}
