package validation;


import static validation.StringValidationPresentContent.isBlankOrEmpty;
import static validation.StringValidationPresentContent.isNull;

public class ExplanationValidation extends RuntimeException {
    public static boolean fieldsContainsValue(String description) {

        try {
            if (isNull(description)) throw new NullPointerException("Descrição de Explicação não pode ser nulo");

            if (isBlankOrEmpty(description)) throw new IllegalArgumentException("Descrição de Explicação não pode ser vazio");






            return true;

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }


    }
}
