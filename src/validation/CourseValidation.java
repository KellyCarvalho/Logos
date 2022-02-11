package validation;

import static validation.StringValidationPresentContent.isBlankOrEmpty;
import static validation.StringValidationPresentContent.isNull;

public class CourseValidation extends RuntimeException {


    public static boolean isValidCode(String code) {

        try {
            boolean validationCode = code!=null? code.matches("[a-z0-9^-]+"):false;


            if (!validationCode)
                throw new IllegalArgumentException("Código do curso não é válido, deve ter caracteres de a-z e algarismos de 0-9 e tudo bem se tiver o hífen em sua composição");
            return true;

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return false;
        }


    }

    public static boolean isValidEstimatedTime(int estimatedTime) {
        try {
            if (estimatedTime < 1) throw new IllegalArgumentException("Tempo estimado não pode ser menor que 1 hora");

            if (estimatedTime > 20) throw new IllegalArgumentException("Tempo estimado não pode ultrapassar 20 horas");


            return true;

        } catch (IllegalArgumentException e) {

            System.out.println(e.getMessage());

            return false;

        }
    }

    public static boolean fieldsContainsValue(String name, String code) {
        try {

            if (isNull(name))
                throw new NullPointerException("Nome do Curso não pode ser nulo ");

            if (isBlankOrEmpty(name))
                throw new IllegalArgumentException("Nome do Curso não pode ser vazio ");


            if (isNull(code))
                throw new NullPointerException("Código do curso não pode ser nulo ");

            if (isBlankOrEmpty(code))
                throw new IllegalArgumentException("Código do curso não pode ser vazio ");



            return true;

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }




}
