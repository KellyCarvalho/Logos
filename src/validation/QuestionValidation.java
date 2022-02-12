package validation;




import static validation.StringValidationPresentContent.isBlankOrEmpty;
import static validation.StringValidationPresentContent.isNull;

public class QuestionValidation extends RuntimeException{

    public static boolean fieldsContainsValue(String statement) {

        try {
            if (isNull(statement))
                throw new NullPointerException("Enunciado da questão não pode ser nulo");

            if (isBlankOrEmpty(statement))
                throw new IllegalArgumentException("Enunciado da questão não pode ser vazio");
            return true;

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }


    }


}
