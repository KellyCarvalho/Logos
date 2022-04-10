package commonValidator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static br.com.logos.commonValidator.StringValidator.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringValidatorTest {

    @ParameterizedTest
    @NullAndEmptySource
    void isNotBlankEmptyOrNullShouldThrowIllegalArgumentExceptionIfTextReceivedIsEmptyOrNull(String text) {
        assertThrows(IllegalArgumentException.class, () -> isNotBlankEmptyOrNull(text, "Não pode ser vazio ou nulo"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"texto"})
    void isNotBlankEmptyOrNullShouldNotThrowExceptionIfTextReceivedIsNotEmptyOrNull(String text) {
        assertDoesNotThrow(() -> isNotBlankEmptyOrNull(text, "Não pode ser vazio ou nulo"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"#67678678", "798738", "#65789", "!99-09"})
    void isValidColorShouldThrowIllegalArgumentExceptionIfColorCodeIsInvalid(String colorCode) {
        assertThrows(IllegalArgumentException.class, () -> isValidColor(colorCode, "Cor não é válida"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"#363636", "#4F4F4F", "#808080"})
    void isValidColorShouldNotThrowExceptionIfColorCodeIsValid(String colorCode) {
        assertDoesNotThrow(() -> isValidColor(colorCode, "Cor não é válida"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Javá", "j ava", "j#v@", "j @v4"})
    void isValidCodeShouldThrowIllegalArgumentExceptionIfCodeIsInvalid(String code) {
        assertThrows(IllegalArgumentException.class, () -> isValidCode(code, "Código não é válido"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"java", "java-12", "java-java"})
    void isValidCodeShouldNotThrowExceptionIfCodeIsValid(String code) {
        assertDoesNotThrow(() -> isValidCode(code, "Código não é válido"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Javá-12", "j4ava", "j#v@", "j @v4"})
    void isValidCodeWithoutNumbersShouldThrowIllegalArgumentExceptionIfCodeIsInvalid(String code) {
        assertThrows(IllegalArgumentException.class, () -> doesCodeContainsOnlyLettersInLowerCaseAndHyphen(code, "Código não é válido"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"java", "java-", "java-java"})
    void isValidCodeWithoutNumbersShouldNotThrowExceptionIfCodeIsValid(String code) {
        assertDoesNotThrow(() -> doesCodeContainsOnlyLettersInLowerCaseAndHyphen(code, "Código não é válido"));
    }
}
