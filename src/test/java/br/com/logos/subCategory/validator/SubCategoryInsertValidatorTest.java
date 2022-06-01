package br.com.logos.subCategory.validator;


import br.com.logos.subCategory.SubCategoryInsertDTO;
import br.com.logos.subCategory.SubCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.*;

public class SubCategoryInsertValidatorTest {

    private SubCategoryRepository repository;
    private SubCategoryInsertValidator dtoValidator;
    private Errors errors;

    @BeforeEach
    void setUp(){
        repository = mock(SubCategoryRepository.class);
        dtoValidator = new SubCategoryInsertValidator(repository);
        errors = mock(Errors.class);
    }

   @Test
   void validateShouldShowErrorIfCodeAlreadyExists(){
        when(repository.existsByCode("java")).thenReturn(true);

       SubCategoryInsertDTO dto = SubCategoryInsertDTO.builder().code("java").build();
       dtoValidator.validate(dto, errors);

        verify(errors).rejectValue("code", "subCategory.code.already.exists");
    }

    @Test
    void validateShouldNotShowErrorIfCodeDoesNotExist(){
        SubCategoryInsertDTO dto = SubCategoryInsertDTO.builder().code("java").build();
        dtoValidator.validate(dto, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}
