package br.com.logos.category;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static br.com.logos.category.enums.CategoryStatus.ACTIVE;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CategoryTest {

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
    void constructorShouldThrowIllegalArgumentExceptionIfEmptyOrNullCode(String code) {
        assertThrows(IllegalArgumentException.class, () -> new Category("java", code));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void constructorShouldThrowIllegalArgumentExceptionIfEmptyOrNullName(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Category(name, "java"));
    }

    @ParameterizedTest
    @CsvSource({"Java", "Java oo", "java"})
    void constructorShouldNotThrowIllegalArgumentExceptionIfIsNotEmptyOrNullName(String name) {
        assertDoesNotThrow(() -> new Category(name, "java"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"!191970", "#!78839", "#4545433"})
    void constructorShouldThrowIllegalArgumentExceptionIfColorCodeIsInvalid(String colorCode) {
        assertThrows(IllegalArgumentException.class,
                () -> new Category("Java", "java", "java é legal", ACTIVE, 0, "umaurlqualquer.com.br", colorCode));
    }

    @ParameterizedTest
    @ValueSource(strings = {"#191970", "#00008B", "#6495ED"})
    void constructorShouldNotThrowExceptionIfColorCodeIsValid(String colorCode) {
        assertDoesNotThrow(() -> new Category("Java", "java", "java é legal", ACTIVE, 0, "umaurlqualquer.com.br", colorCode));
    }
}
