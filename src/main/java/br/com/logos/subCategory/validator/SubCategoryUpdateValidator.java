package br.com.logos.subCategory.validator;

import br.com.logos.subCategory.SubCategoryRepository;
import br.com.logos.subCategory.SubCategoryUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class SubCategoryUpdateValidator implements Validator {

    private final SubCategoryRepository subCategoryRepository;

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
