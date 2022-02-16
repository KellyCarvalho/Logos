package avalon.activity.validation;

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
