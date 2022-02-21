package Logos.commonValidator;

public class UtilsValidator {

    public static boolean isValidOrder(int order, String message) {
        if (order < 1)
            throw new IllegalArgumentException(message);
        return true;
    }
}
