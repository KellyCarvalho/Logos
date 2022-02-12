package avalon.activity.validation;

import avalon.section.Section;

import static commonValidation.StringValidationPresentContent.isBlankOrEmpty;
import static commonValidation.StringValidationPresentContent.isNull;

public class ActivityValidation extends RuntimeException {


    public static boolean isValidCode(String code) {

        try {
            boolean validationCode = code != null ? code.matches("[a-z0-9^-]+") : false;


            if (!validationCode)
                throw new IllegalArgumentException("Código de Atividade não é válido, deve ter caracteres de a-z e algarismos de 0-9 e tudo bem se tiver o hífen em sua composição");
            return true;

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }


    }

    public static boolean fieldsContainsValue(String title, String code, Section section) {

        try {
            if (isNull(title)) throw new NullPointerException("Título de Atividade não pode ser nulo");

            if (isBlankOrEmpty(title)) throw new IllegalArgumentException("Título de Atividade não pode ser vazia ou em branco");

            if (isNull(code)) throw new NullPointerException("Código de Atividade não pode ser nulo");


            if (isBlankOrEmpty(code)) throw new IllegalArgumentException("Código de Atividade não pode ser vazio ou em branco");


            if (section == null) throw new NullPointerException("Seção de Atividade não pode ser nula");


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
