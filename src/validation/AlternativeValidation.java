package validation;

import entities.Question;

import static validation.StringValidationPresentContent.isBlankOrEmpty;
import static validation.StringValidationPresentContent.isNull;

public class AlternativeValidation extends RuntimeException{

    public static boolean fieldsContainsValue(String description, String explanationAnswer, Question question) {

        try {
            if (isNull(description))
                throw new NullPointerException("Descrição da alternativa não pode ser nula");

            if (isBlankOrEmpty(description))
                throw new IllegalArgumentException("Descrição da alternativa  não pode ser vazia");

            if (isNull(explanationAnswer))
                throw new NullPointerException("Explicação da alternativa não pode ser nula");

            if (isBlankOrEmpty(explanationAnswer))
                throw new IllegalArgumentException("Explicação da alternativa  não pode ser vazia");

            if (question==null)
                throw new NullPointerException("Questão da alternativa  não pode ser nula");



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
