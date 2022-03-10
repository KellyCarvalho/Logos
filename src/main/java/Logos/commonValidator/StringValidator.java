package Logos.commonValidator;

public class StringValidator {

    public static boolean isNotBlankEmptyOrNull(String text, String message) {
        if (text == null || text.isBlank() || text.isEmpty()) throw new IllegalArgumentException(message);
        return false;
    }

    public static boolean isValidColor(String color, String message) {
        boolean isValidCode = color != null ? color.matches("^#([a-fA-F0-9]){3}(([a-fA-F0-9]){3})?$") : false;
        if (!isValidCode) throw new IllegalArgumentException(message);
        return true;
    }

    public static boolean isValidCode(String code, String message) {
        boolean isValidCode = code != null ? code.matches("[a-z0-9^-]+") : false;
        if (!isValidCode) throw new IllegalArgumentException(message);
        return true;
    }

    public static boolean doesCodeContainsOnlyLettersInLowerCaseAndHyphen(String code, String message) {
        boolean isValidCode = code != null ? code.matches("[a-z-]+") : false;
        if (!isValidCode) throw new IllegalArgumentException(message);
        return true;
    }
}
