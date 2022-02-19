package commonValidation;

public class ValidatorUtils {

    public static boolean isValidOrder(int order, String message) {
        if (order < 1)
            throw new IllegalArgumentException(message);
        return true;
    }
}
