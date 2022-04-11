package br.com.logos.commonValidator;

public class StringValidator {

    public static void isNotBlankEmptyOrNull(String text, String message) {
        if (text == null || text.isBlank() || text.isEmpty()) throw new IllegalArgumentException(message);
    }

    public static void isValidColor(String color, String message) {
        boolean isValidCode = color != null ? color.matches("^#([a-fA-F0-9]){6}?$|^[\s]*$") : false;
        if (!isValidCode) throw new IllegalArgumentException(message);
    }

    public static void isValidCode(String code, String message) {
        boolean isValidCode = code != null ? code.matches("[a-z0-9^-]+") : false;
        if (!isValidCode) throw new IllegalArgumentException(message);
    }

    public static void doesCodeContainsOnlyLettersInLowerCaseAndHyphen(String code, String message) {
        boolean isValidCode = code != null ? code.matches("[a-z-]+") : false;
        if (!isValidCode) throw new IllegalArgumentException(message);
    }
}
