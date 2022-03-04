import Logos.category.Category;
import Logos.category.enums.CategoryStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

    @ParameterizedTest
    @CsvSource({"Java", "J@v@", "ja va", "jáva"})
    void shouldThrowArgumentExceptionIfSpecialCharactersSpacesAndLettersInUpperCaseInCode(String code) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Category("java", code);
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowIllegalArgumentExceptionToEmptyOrNullCode(String code) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Category("Java", code);
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowIllegalArgumentExceptionToEmptyOrNullName(String name) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Category(name, "java");
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"!191970", "#!78839", "#4545433"})
    void shouldThrowIllegalArgumentExceptionIfColorCodeIsInvalid(String colorCode) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Category("Java", "java", "java é legal", CategoryStatus.ACTIVE,
                    0, "umaurlqualquer.com.br", colorCode);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"#191970", "#00008B", "#6495ED"})
    void shouldNotThrowIllegalArgumentExceptionIfColorCodeIsValid(String colorCode) {
        assertDoesNotThrow(() -> {
            new Category("Java", "java", "java é legal", CategoryStatus.ACTIVE,
                    0, "umaurlqualquer.com.br", colorCode);
        });
    }

    @Test
    void isActiveShouldReturnTrueIfStatusIsActive() {
        Category category = new Category("Java", "java", "java é legal", CategoryStatus.ACTIVE,
                0, "umaurlqualquer.com.br", "#6A5ACD");
        assertTrue(category.isActive());
    }

    @Test
    void isActiveShouldReturnFalseIfStatusIsDisabled() {
        Category category = new Category("Java", "java", "java é legal", CategoryStatus.DISABLED,
                0, "umaurlqualquer.com.br", "#6A5ACD");
        assertFalse(category.isActive());
    }
}
