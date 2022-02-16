package commonValidation;

public class ObjectValidation {

    public static boolean isObjectValid(Object object, String message) {

        if (object == null) throw new IllegalArgumentException(message);
        return true;
    }
}
