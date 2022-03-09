import Logos.category.Category;
import Logos.category.enums.CategoryStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static Logos.category.Category.getActiveCategories;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {
    public static List<Category> categories;

    @BeforeAll
    public static void setUp() {
        categories = Arrays.asList(
                new Category("Programação", "programacao", CategoryStatus.ACTIVE),
                new Category("Devops", "devops", CategoryStatus.ACTIVE),
                new Category("Business", "business", CategoryStatus.ACTIVE),
                new Category("Produtividade", "produtividade", CategoryStatus.DISABLED),
                new Category("Comunicação", "comunicacao", CategoryStatus.DISABLED));
    }

    @ParameterizedTest
    @CsvSource({"Java", "J@v@", "ja va", "jáva"})
    void constructorShouldThrowArgumentExceptionIfInvalidCode(String code) {
        assertThrows(IllegalArgumentException.class, () -> new Category("java", code));
    }

    @ParameterizedTest
    @CsvSource({"java", "java-oo", "java-", "j-v"})
    void constructorShouldNotThrowExceptionIfValidCode(String code) {
        assertDoesNotThrow(() -> new Category("java", code));
    }


    @ParameterizedTest
    @NullAndEmptySource
    void ConstructorShouldThrowIllegalArgumentExceptionIfEmptyOrNullCode(String code) {
        assertThrows(IllegalArgumentException.class, () -> new Category("java", code));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void ConstructorShouldThrowIllegalArgumentExceptionIfEmptyOrNullName(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Category(name, "java"));
    }

    @ParameterizedTest
    @CsvSource({"Java", "Java oo", "java"})
    void ConstructorShouldNotThrowIllegalArgumentExceptionIfIsNotEmptyOrNullName(String name) {
        assertDoesNotThrow(() -> new Category(name, "java"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"!191970", "#!78839", "#4545433"})
    void constructorShouldThrowIllegalArgumentExceptionIfColorCodeIsInvalid(String colorCode) {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new Category("Java", "java", "java é legal", CategoryStatus.ACTIVE,
                            0, "umaurlqualquer.com.br", colorCode);
                });
    }

    @ParameterizedTest
    @ValueSource(strings = {"#191970", "#00008B", "#6495ED"})
    void constructorShouldNotThrowExceptionIfColorCodeIsValid(String colorCode) {
        assertDoesNotThrow(
                () -> {
                    new Category("Java", "java", "java é legal", CategoryStatus.ACTIVE,
                            0, "umaurlqualquer.com.br", colorCode);
                });
    }

    @Test
    void getActiveCategoriesShouldReturnOnlyActivesCategories() {
        List<Category> categoriesTest = Arrays.asList(
                new Category("Programação", "programacao", CategoryStatus.ACTIVE),
                new Category("Devops", "devops", CategoryStatus.ACTIVE),
                new Category("Business", "business", CategoryStatus.ACTIVE));
        List<Category> categoriesActives = getActiveCategories(categories);
        assertTrue(categoriesActives.containsAll(categoriesTest));
    }

    @Test
    void getActiveCategoriesShouldNotReturnDisabledCategories() {
        List<Category> categories = Arrays.asList(
                new Category("Produtividade", "produtividade", CategoryStatus.DISABLED),
                new Category("Comunicação", "comunicacao", CategoryStatus.DISABLED)
        );
        List<Category> categoriesActives = getActiveCategories(categories);
        assertFalse(categoriesActives.containsAll(categories));
    }
}
