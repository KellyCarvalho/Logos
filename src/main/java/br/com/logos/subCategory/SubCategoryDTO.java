package br.com.logos.subCategory;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryDTO {

    //TODO podem ser final
    private final String name;
    private String code;
    private String studyGuide;

    public SubCategoryDTO(String name, String code, String studyGuide) {
        this.name = name;
        this.code = code;
        this.studyGuide = studyGuide;
    }

    public SubCategoryDTO(SubCategory subCategory){
        this.name = subCategory.getCode();
        this.code = subCategory.getCode();
        this.studyGuide = subCategory.getStudyGuide();
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    //TODO isolar métodos que não são responsabilidades do controller
    //TODO fazer lambda
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
