package avalon.activity.validation;


import static commonValidation.StringValidationPresentContent.isBlankOrEmpty;
import static commonValidation.StringValidationPresentContent.isNull;

public class ExplanationValidation extends RuntimeException {
    public static boolean fieldsContainsValue(String description) {

        try {
            if (isNull(description)) throw new NullPointerException("Descrição de Explicação não pode ser nulo");

            if (isBlankOrEmpty(description)) throw new IllegalArgumentException("Descrição de Explicação não pode ser vazio ou em branco");






            return true;

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }


    }
}
