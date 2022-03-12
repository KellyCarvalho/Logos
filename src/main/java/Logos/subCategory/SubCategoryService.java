package Logos.subCategory;

import java.util.List;

public class SubCategoryService {

    public static List<SubCategory> getSubCategoriesWithoutDescription(List<SubCategory> subCategories) {
        return subCategories.stream().filter(SubCategory::isEmptyDescription).toList();
    }

    public static Long getQuantitySubCategoriesActivesWithDescription(List<SubCategory> subCategories) {
        return subCategories.stream().filter(subCategory -> !subCategory.isEmptyDescription()).filter(SubCategory::isActive).count();
    }
}
