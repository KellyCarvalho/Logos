package br.com.logos.subCategory;

import br.com.logos.course.Course;

import java.util.Comparator;
import java.util.List;

public interface ActiveSubCategoriesWithCoursesProjection {

    String getName();
    String getCode();
    List<Course> getCourses();

    default List<Course> getActiveSubCategoriesWithPublicCourse(){
        return getCourses().stream()
                .filter(Course::isVisibility)
                .sorted(Comparator.comparing(course -> course.getSubCategoryOrder())).toList();
    }
}
