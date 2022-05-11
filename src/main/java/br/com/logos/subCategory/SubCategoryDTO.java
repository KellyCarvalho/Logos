package br.com.logos.subCategory;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SubCategoryDTO {

    private final String name;
    private final String code;
    private final String studyGuide;

    public SubCategoryDTO(SubCategory subCategory) {
        this.name = subCategory.getCode();
        this.code = subCategory.getCode();
        this.studyGuide = subCategory.getStudyGuide();
    }

    public static List<SubCategoryDTO> getActiveSubcategoriesByCategory(List<SubCategory> subCategories, Long categoryId) {
        List<SubCategoryDTO> subCategoriesDTO = new ArrayList<>();
        subCategories.forEach(subCategory -> {
            if (subCategory.getCategoryId() == categoryId) {
                subCategoriesDTO.add(new SubCategoryDTO(subCategory));

            }
        });
        return subCategoriesDTO;
    }
}
