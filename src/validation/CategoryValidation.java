package validation;

import entities.Section;

import static validation.StringValidationPresentContent.isBlankOrEmpty;
import static validation.StringValidationPresentContent.isNull;

public class CategoryValidation extends  RuntimeException{
    public static boolean isValidColor(String color) {

        try {
            boolean validationCode = color != null ? color.matches("^#([a-fA-F0-9]){3}(([a-fA-F0-9]){3})?$") : false;


            if (!validationCode)
                throw new IllegalArgumentException("Cor não é válida");
            return true;

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }


    }
    public static boolean fieldsContainsValue(String name, String code) {

        try {
            if (isNull(name)) throw new NullPointerException("Título de Atividade não pode ser nulo");

            if (isBlankOrEmpty(name)) throw new IllegalArgumentException("Título de Atividade não pode ser vazio");

            if (isNull(code)) throw new NullPointerException("Código de Atividade não pode ser nulo");


            if (isBlankOrEmpty(code)) throw new IllegalArgumentException("Código de Atividade não pode ser vazio");





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

