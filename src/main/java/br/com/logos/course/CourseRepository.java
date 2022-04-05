package br.com.logos.course;

import br.com.logos.category.enums.CategoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findCoursesByVisibilityAndSubCategoryCategoryStatusOrderBySubCategoryCategoryOrder(boolean visibility, CategoryStatus status);
    List<Course> findAllBySubCategoryCategoryId(Long id);
}
