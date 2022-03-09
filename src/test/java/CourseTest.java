import Logos.category.Category;
import Logos.course.Course;
import Logos.subCategory.SubCategory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;

import static Logos.course.Course.*;
import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {
    private static SubCategory subCategory;
    private static Category category;
    private static List<Course> courses = new ArrayList<>();
    private static List<String> instructorsNames;


    @BeforeAll
    public static void setUp() {
        category = new Category("programação", "programacao");
        subCategory = new SubCategory("java", "java", category);
        courses.add(new Course("java2", "java2", 10, true, "Iniciantes",
                "Paulo", "Curso de Java", "java", subCategory));

        courses.add(new Course("java3", "java3", 10, true, "Iniciantes",
                "Camila", "Curso de Java", "java", subCategory));

        courses.add(new Course("java4", "java4", 10, true, "Iniciantes",
                "Tamires", "Curso de Java", "java", subCategory));
        instructorsNames = Arrays.asList("Paulo", "Camila", "Tamires");
    }

    //TODO testar construtor
    @ParameterizedTest
    @CsvSource({"Java", "J@v@", "ja va", "jáva"})
    void shouldThrowArgumentExceptionIfInvalidCode(String code) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Course("Java", code, 10, "Paulo", subCategory);
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowIllegalArgumentExceptionsIfEmptyOrNullCode(String code) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Course("Java", code, 10, "Paulo", subCategory);
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowIllegalArgumentExceptionIfEmptyOrNullName(String name) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Course(name, "java", 10, "Paulo", subCategory);
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowIllegalArgumentExceptionIfEmptyOrNullInstructorName(String instructorName) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Course("Java", "java", 10, instructorName, subCategory);
        });
    }

    @ParameterizedTest
    @NullSource
    void shouldThrowIllegalArgumentExceptionIfNullSubCategory(SubCategory subCategory) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Course("Java", "java", 10, "Paulo", subCategory);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 21})
    void shouldThrowIllegalArgumentExceptionIfIsBetweenZeroAndTwenty(int estimatedTime) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Course("Java", "java", estimatedTime, "Paulo", subCategory);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 20})
    void shouldNotThrowExceptionIfIsBetweenZeroAndTwenty(int estimatedTime) {
        assertDoesNotThrow(() -> {
            new Course("Java", "java", estimatedTime, "Paulo", subCategory);
        });
    }

    @Test
    void hasAnyPrivateCourseShouldReturnFalseIfDoesNotExistAnyPrivateCourse() {
        assertFalse(hasAnyPrivateCourse(courses));
    }

    @Test
    void hasAnyPrivateCourseShouldReturnTrueIfExistAnyPrivateCourse() {
        courses.add(new Course("javaTest", "java", 10, "Alana", subCategory));
        assertTrue(hasAnyPrivateCourse(courses));
    }

    @Test
    void getInstructorsWithCourseQuantitiesShouldReturnInstructorsNamesAndQuantitiesCourses() {
        String instructorsWithCoursesQuantities = "{Camila=1, Paulo=1, Tamires=1}";
        assertEquals(instructorsWithCoursesQuantities, getInstructorsWithCourseQuantities(courses).toString());
    }
}
