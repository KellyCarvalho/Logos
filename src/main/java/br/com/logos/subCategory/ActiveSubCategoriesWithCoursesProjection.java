package br.com.logos.subCategory;

import br.com.logos.course.Course;
import org.springframework.beans.factory.annotation.Value;

import java.util.Comparator;
import java.util.List;

public interface ActiveSubCategoriesWithCoursesProjection {

    @Value("#{target.name}")
    String getName();
    @Value("#{target.code}")
    String getCode();
    @Value("#{target.courses}")
    List<Course> getCourses();

    default List<Course> getActiveSubCategoriesWithPublicCourse(){
        return getCourses().stream()
                .filter(Course::isVisibility)
                .sorted(Comparator.comparing(course -> course.getSubCategoryOrder())).toList();
    }
}
