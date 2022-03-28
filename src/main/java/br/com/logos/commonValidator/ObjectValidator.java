package br.com.logos.commonValidator;

public class ObjectValidator {

    public static void isObjectValid(Object object, String message) {
        if (object == null) throw new IllegalArgumentException(message);
    }
}
