package br.com.logos.subCategory.validator;


import br.com.logos.subCategory.SubCategoryInsertDTO;
import br.com.logos.subCategory.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@RequiredArgsConstructor
@Component
public class SubCategoryInsertValidator implements Validator {

    private final SubCategoryRepository subCategoryRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return SubCategoryInsertDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SubCategoryInsertDTO dto = (SubCategoryInsertDTO) target;
        if (subCategoryRepository.existsByCode(dto.getCode())){
            errors.rejectValue("code", "subCategory.code.already.exists");
        }
    }
}
