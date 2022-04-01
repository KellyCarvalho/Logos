package br.com.logos.category;

import br.com.logos.category.enums.CategoryStatus;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.Objects;

public class CategoryService {

    public static List<Category> getActiveCategories(List<Category> categories) {
        return categories.stream().filter(Category::isActive).toList();
    }

    public Category toCategory(HttpServletRequest request) {
        String order = request.getParameter("order");
        String id = request.getParameter("id");

        int convertOrder = order != null && order != "" ? Integer.parseInt(order) : 0;
        Category category = new Category(request.getParameter("name"), request.getParameter("code"),
                request.getParameter("description"), request.getParameter("studyGuide"),
                Objects.equals(request.getParameter("status"), "ACTIVE") ? CategoryStatus.ACTIVE : CategoryStatus.DISABLED,
                convertOrder,  request.getParameter("imageUrl"), request.getParameter("colorCode"));
        if (id != null) {
            category.setId(Long.parseLong(id));
        }
        return category;
    }
}
