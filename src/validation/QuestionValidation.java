package validation;


import entities.TypeQuestion;

import static validation.StringValidationPresentContent.isBlankOrEmpty;
import static validation.StringValidationPresentContent.isNull;

public class QuestionValidation extends RuntimeException{

    public static boolean fieldsContainsValue(String statement) {

        try {
            if (isNull(statement)) throw new NullPointerException("Enunciado da questão não pode ser nulo");

            if (isBlankOrEmpty(statement)) throw new IllegalArgumentException("Enunciado da questão não pode ser vazio");






            return true;

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return false;
        }


    }

      public static boolean isValidTypeQuestion(TypeQuestion typeQuestion){

        try {
            if(!(typeQuestion.equals(TypeQuestion.SINGLE_ANSWER)||typeQuestion.equals(TypeQuestion.MULTIPLE_CHOICE)||typeQuestion.equals(TypeQuestion.TRUE_OR_FALSE)))
                throw new IllegalArgumentException("Tipo de questão é inválido");
            return true;
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return false;

        }



      }

}
