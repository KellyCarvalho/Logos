package course;

import br.com.logos.category.Category;
import br.com.logos.course.Course;
import br.com.logos.subCategory.SubCategory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {

    private static SubCategory subCategory;
    private static Category category;
    private static List<Course> courses = new ArrayList<>();

    @BeforeAll
    public static void setUp() {
        category = new Category("programação", "programacao");

        subCategory = new SubCategory("java", "java", category);
    }

    @ParameterizedTest
    @CsvSource({"Java", "J@v@", "ja va", "jáva"})
    void constructorShouldThrowArgumentExceptionIfInvalidCode(String code) {
        assertThrows(IllegalArgumentException.class, () -> new Course("java", code, 10, "Paulo", subCategory));
    }

    @ParameterizedTest
    @CsvSource({"java", "java-oo", "java-", "j-v"})
    void constructorShouldNotThrowExceptionIfValidCode(String code) {
        assertDoesNotThrow(() -> new Course("java", code, 10, "Paulo", subCategory));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void constructorShouldThrowIllegalArgumentExceptionIfEmptyOrNullCode(String code) {
        assertThrows(IllegalArgumentException.class, () -> new Course("java", code, 10, "Paulo", subCategory));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void constructorShouldThrowIllegalArgumentExceptionIfEmptyOrNullName(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Course(name, "java", 10, "Paulo", subCategory));
    }

    @ParameterizedTest
    @CsvSource({"Java", "Java oo", "java"})
    void constructorShouldNotThrowIllegalArgumentExceptionIfIsNotEmptyOrNullName(String name) {
        assertDoesNotThrow(() -> new Course(name, "java", 10, "Paulo", subCategory));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowIllegalArgumentExceptionIfEmptyOrNullInstructorName(String instructorName) {
        assertThrows(IllegalArgumentException.class, () -> new Course("Java", "java", 10, instructorName, subCategory));
    }

    @ParameterizedTest
    @CsvSource({"Paulo", "paulo", "paulo paulo"})
    void constructorShouldNotThrowIllegalArgumentExceptionIfIsNotEmptyOrNullInstructorName(String instructorName) {
        assertDoesNotThrow(() -> new Course("Java", "java", 10, instructorName, subCategory));
    }

    @ParameterizedTest
    @NullSource
    void shouldThrowIllegalArgumentExceptionIfNullSubCategory(SubCategory subCategory) {
        assertThrows(IllegalArgumentException.class, () -> new Course("Java", "java", 10, "Paulo", subCategory));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 21})
    void shouldThrowIllegalArgumentExceptionIfIsBetweenZeroAndTwenty(int estimatedTime) {
        assertThrows(IllegalArgumentException.class, () -> new Course("Java", "java", estimatedTime, "Paulo", subCategory));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 20})
    void shouldNotThrowExceptionIfIsBetweenZeroAndTwenty(int estimatedTime) {
        assertDoesNotThrow(() -> new Course("Java", "java", estimatedTime, "Paulo", subCategory));
    }
}
