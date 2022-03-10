import Logos.category.Category;
import Logos.course.Course;
import Logos.subCategory.SubCategory;
import Logos.subCategory.enums.SubCategoryStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static Logos.course.CourseService.getSubCategoryName;
import static Logos.course.CourseService.getTotalCourseHours;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CourseServiceTest {

    private static List<Course> courses;
    private static Category category;
    private static SubCategory subCategory;

    @BeforeEach
    private void setUp() {
        category = new Category("programação", "programacao");

        subCategory = new SubCategory("java", "java", "java", SubCategoryStatus.ACTIVE, 2, category);

        courses = Arrays.asList(
                new Course("java2", "java2", 10, true, "Iniciantes",
                        "Paulo", "Curso de Java", "java", subCategory),
                new Course("java3", "java3", 10, true, "Iniciantes",
                        "Camila", "Curso de Java", "java", subCategory),
                new Course("java4", "java4", 10, true, "Iniciantes",
                        "Tamires", "Curso de Java", "java", subCategory));
    }

    @Test
    void getTotalCourseHoursShouldReturnTotalCourseHours() {
        assertEquals(getTotalCourseHours(courses), 30);
    }

    @Test
    void getSubCategoryNameShouldReturnNameSubCategoryWithBrTags() {
        assertEquals(getSubCategoryName(courses), "java</br> Descrição: java</br></br> java</br> Descrição: java</br></br> java</br> Descrição: java</br></br>");
    }
}
