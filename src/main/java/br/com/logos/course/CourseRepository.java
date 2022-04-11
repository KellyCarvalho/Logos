package br.com.logos.course;

import br.com.logos.category.enums.CategoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByVisibilityAndSubCategory_Category_StatusOrderBySubCategory_Category_Order(boolean visibility, CategoryStatus status);
}
