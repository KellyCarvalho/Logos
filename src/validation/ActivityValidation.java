package validation;

import entities.Section;
import entities.Type;

import static validation.StringValidationPresentContent.isBlankOrEmpty;
import static validation.StringValidationPresentContent.isNull;

public class ActivityValidation extends RuntimeException {


    public static boolean toValidCode(String code) {

        try {
            boolean validationName = code.matches("[a-z0-9^-]+");


            if (!validationName)
                throw new IllegalArgumentException("Código não é válido, deve ter caracteres de a-z e algarismos de 0-9 e tudo bem se tiver o hífen em sua composição");
            return true;

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return false;
        }


    }

    public static boolean fieldsContainsValue(String title, String code, Type type, Section section) {

        try {

            if (isBlankOrEmpty(title)) throw new IllegalArgumentException("Título não pode ser vazio");

            if (isNull(title)) throw new NullPointerException("Título não pode ser nulo");

            if (isBlankOrEmpty(code)) throw new IllegalArgumentException("Código não pode ser vazio");

            if (isNull(code)) throw new NullPointerException("Código não pode ser nulo");


            if (section == null) throw new NullPointerException("Seção não pode ser nula");


            return true;

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return false;
        }


    }

    public static boolean isValidOrder(int order) {
        try {
            if (order < 0)
                throw new IllegalArgumentException("Ordem não pode ter valor menor que 0");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }


    }
}
