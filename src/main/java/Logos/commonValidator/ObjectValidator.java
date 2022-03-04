package Logos.commonValidator;

public class ObjectValidator {

    public static boolean isObjectValid(Object object, String message) {
        if (object == null) throw new IllegalArgumentException(message);
        return true;
    }
}
