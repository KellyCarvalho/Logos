package br.com.logos.category.validator;


import br.com.logos.category.CategoryInsertDTO;
import br.com.logos.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class CategoryInsertValidator implements Validator {

    private final CategoryRepository categoryRepository;

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
