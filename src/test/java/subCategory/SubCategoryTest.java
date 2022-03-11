package subCategory;

import Logos.category.Category;
import Logos.subCategory.SubCategory;
import Logos.subCategory.enums.SubCategoryStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import static Logos.subCategory.enums.SubCategoryStatus.ACTIVE;
import static Logos.subCategory.enums.SubCategoryStatus.DISABLED;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SubCategoryTest {
    private static Category category;

    @BeforeAll
    public static void setUp() {
        category = new Category("programação", "programacao");
    }

    @ParameterizedTest
    @CsvSource({"Java", "J@v@", "ja va", "jáva"})
    void constructorShouldThrowArgumentExceptionIfInvalidCode(String code) {
        assertThrows(IllegalArgumentException.class, () -> new SubCategory("java", code, category));
    }

    @ParameterizedTest
    @CsvSource({"java", "java-oo", "java-", "j-v"})
    void constructorShouldNotThrowExceptionIfValidCode(String code) {
        assertDoesNotThrow(() -> new SubCategory("java", code, category));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void constructorShouldThrowIllegalArgumentExceptionIfEmptyOrNullCode(String code) {
        assertThrows(IllegalArgumentException.class, () -> new SubCategory("java", code, category));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void constructorShouldThrowIllegalArgumentExceptionIfEmptyOrNullName(String name) {
        assertThrows(IllegalArgumentException.class, () -> new SubCategory(name, "java", category));
    }

    @ParameterizedTest
    @CsvSource({"Java", "Java oo", "java"})
    void constructorShouldNotThrowIllegalArgumentExceptionIfIsNotEmptyOrNullName(String name) {
        assertDoesNotThrow(() -> new SubCategory(name, "java", category));
    }

    @ParameterizedTest
    @NullSource
    void constructorShouldThrowIllegalArgumentExceptionToEmptyOrNullCategory(Category category) {
        assertThrows(IllegalArgumentException.class, () -> new SubCategory("java", "java", category));
    }
}
