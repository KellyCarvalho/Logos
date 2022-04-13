package br.com.logos.course;

import br.com.logos.category.enums.CategoryStatus;
import br.com.logos.subCategory.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByVisibilityAndSubCategory_Category_StatusOrderBySubCategory_Category_Order(boolean visibility, CategoryStatus status);

    @Query(value = """
        SELECT category.name `categoryName`, count(course.id) `quantity` 
        FROM Category category 
        LEFT JOIN Subcategory subcategory ON category.id = subcategory.fk_category 
        LEFT JOIN Course course ON subcategory.id = course.fk_subcategory 
        GROUP BY category.id 
        ORDER BY `quantity` DESC
        """, nativeQuery = true)
    List<CourseByCategoryProjection> findAllCoursesCountByCategory();

    //TODO colocar o nome projection no final
    @Query(value = """
        SELECT instructor_name `instructorName`, COUNT(c.id)  `quantity`
        FROM Course c
        GROUP BY instructorName 
        ORDER BY quantity DESC LIMIT 1
        """, nativeQuery = true)
    List<CoursesQuantityByInstructorname> findAllInstructorCountCourses();

    Page<Course> findAllBySubCategory(SubCategory subCategory, Pageable pageable);
}
