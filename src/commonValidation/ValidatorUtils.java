package commonValidation;

public class ValidatorUtils {

    public static boolean isValidOrder(int order, String message) {
        if (order <= 0)
            throw new IllegalArgumentException(message);
        return true;
    }
}
