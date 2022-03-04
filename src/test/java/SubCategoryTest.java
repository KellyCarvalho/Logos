import Logos.category.Category;
import Logos.subCategory.SubCategory;
import Logos.subCategory.enums.SubCategoryStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.*;

public class SubCategoryTest {
    private static Category category;

    @BeforeAll
    public static void initializeDependencies() {
        category = new Category("programação", "programacao");
    }

    @ParameterizedTest
    @CsvSource({"Java", "J@v@", "ja va", "jáva"})
    void shouldThrowArgumentExceptionIfSpecialCharactersSpacesAndLettersInUpperCaseInCode(String code) {
        assertThrows(IllegalArgumentException.class, () -> {
            new SubCategory("java", code, category);
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowIllegalArgumentExceptionToEmptyOrNullCode(String code) {
        assertThrows(IllegalArgumentException.class, () -> {
            new SubCategory("java", code, category);
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowIllegalArgumentExceptionToEmptyOrNullName(String name) {
        assertThrows(IllegalArgumentException.class, () -> {
            new SubCategory(name, "java", category);
        });
    }

    @ParameterizedTest
    @NullSource
    void shouldThrowIllegalArgumentExceptionToEmptyOrNullCategory(Category category) {
        assertThrows(IllegalArgumentException.class, () -> {
            new SubCategory("java", "java", category);
        });
    }

    @Test
    void isActiveShouldReturnTrueIfStatusIsActive() {
        SubCategory subCategory = new SubCategory("java", "java", "entendendo o java", SubCategoryStatus.ACTIVE, 1, category);
        assertTrue(subCategory.isActive());
    }

    @Test
    void isActiveShouldReturnFalseIfStatusIsDisabled() {
        SubCategory subCategory = new SubCategory("java", "java", "entendendo o java", SubCategoryStatus.DISABLED, 1, category);
        assertFalse(subCategory.isActive());
    }
}
