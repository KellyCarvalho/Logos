package validation;

import static validation.StringValidationPresentContent.isBlankOrEmpty;
import static validation.StringValidationPresentContent.isNull;

public class VideoValidation extends RuntimeException {
    public static boolean isValidCode(String code) {

        try {
            boolean validationCode = code != null ? code.matches("[a-z0-9^-]+") : false;


            if (!validationCode)
                throw new IllegalArgumentException("Código de Atividade não é válido, deve ter caracteres de a-z e algarismos de 0-9 e tudo bem se tiver o hífen em sua composição");
            return true;

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return false;
        }


    }

    public static boolean fieldsContainsValue(String url) {

        try {
            if (isNull(url))
                throw new NullPointerException("Url do vídeo não pode ser nula");

            if (isBlankOrEmpty(url))
                throw new IllegalArgumentException("Url do vídeo não pode ser vazia");




            return true;

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }


    }

    public static boolean isValidDuration(int duration) {
        try {
            if (duration<1)
                throw new IllegalArgumentException("Minutos de duração do vídeo não podem ter valor menor que 1");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }


    }
}
