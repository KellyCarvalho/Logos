package br.com.logos.category;

import br.com.logos.category.enums.CategoryStatus;

import java.util.List;
import java.util.Objects;

public class CategoryService {

    public static List<Category> getActiveCategories(List<Category> categories) {
        return categories.stream().filter(Category::isActive).toList();
    }

    public Category toCategory(String id, String name, String code, String description, String studyGuide, String status, String order, String imageUrl, String colorCode) {
        int convertOrder = order != null && order != "" ? Integer.parseInt(order) : 0;
        Category category = new Category(name, code, description, studyGuide, Objects.equals(status, "ACTIVE") ? CategoryStatus.ACTIVE : CategoryStatus.DISABLED, convertOrder, imageUrl, colorCode);
        if (id != null) {
            category.setId(Long.parseLong(id));
        }
        return category;
    }
}
