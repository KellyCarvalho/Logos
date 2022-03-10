package Logos.category;

import java.util.List;

public class CategoryService {
    public static List<Category> getActiveCategories(List<Category> categories) {
        return categories.stream().filter(Category::isActive).toList();
    }
}
