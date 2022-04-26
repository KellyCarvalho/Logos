package br.com.logos.category;

import br.com.logos.course.Course;
import br.com.logos.subCategory.SubCategory;

import java.util.Comparator;
import java.util.List;

public interface CategoryActiveWithSubCategoriesNameProjection {

    String getCode();
    String getName();
    String getImageUrl();
    List<SubCategory> getSubCategories();

    default List<SubCategory> getActiveSubCategoriesWithPublicCourse(){
        return getSubCategories().stream().filter(SubCategory::isActive)
                .filter(subCategory -> subCategory.getCourses().stream().anyMatch(Course::isVisibility))
                .sorted(Comparator.comparing(SubCategory::getOrder))
                .toList();
    }
}