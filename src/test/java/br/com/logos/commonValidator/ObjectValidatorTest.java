package br.com.logos.commonValidator;

import org.junit.jupiter.api.Test;

import static br.com.logos.commonValidator.ObjectValidator.isObjectValid;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ObjectValidatorTest {

    @Test
    void isObjectValidShouldThrowIllegalArgumentExceptionIfObjectIsNull() {
        assertThrows(IllegalArgumentException.class, () -> isObjectValid(null, "Não pode ser null"));
    }

    @Test
    void isObjectValidShouldNotThrowExceptionIfObjectIsNotNull() {
        assertDoesNotThrow(() -> isObjectValid("Java", ""));
    }
}
