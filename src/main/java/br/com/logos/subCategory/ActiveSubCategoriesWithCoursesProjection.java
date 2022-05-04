package br.com.logos.subCategory;

import br.com.logos.course.Course;

import java.util.Comparator;
import java.util.List;

public interface ActiveSubCategoriesWithCoursesProjection {

    String getName();
    String getCode();
    List<Course> getCourses();

    default List<Course> getVisibleCoursesWithActiveSubCategorySortedBySubCategoryOrder(){
        return getCourses().stream()
                .filter(Course::isVisibility)
                .filter(course -> course.getSubCategory().isActive())
                .sorted(Comparator.comparing(course -> course.getSubCategoryOrder())).toList();
    }
}
