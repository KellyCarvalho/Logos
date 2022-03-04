import Logos.category.Category;
import Logos.course.Course;
import Logos.section.Section;
import Logos.subCategory.SubCategory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CourseTest {
    private static SubCategory subCategory;
    private static Category category;
    private static Course course;

    @BeforeAll
   public static void initializeDependencies() {
        category = new Category("programação", "programacao");
        subCategory = new SubCategory("java","java",category);
    }
    @ParameterizedTest
    @CsvSource({"Java", "J@v@", "ja va", "jáva"})
    void shouldThrowArgumentExceptionIfSpecialCharactersSpacesAndLettersInUpperCaseInCode(String code) {
        assertThrows(IllegalArgumentException.class, () -> {
          new Course("Java",code,10,"Paulo",subCategory);
        });
    }

}
