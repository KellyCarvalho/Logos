package avalon.alternative.validation;

import avalon.activity.Question;

import static commonValidation.StringValidationPresentContent.isBlankOrEmpty;
import static commonValidation.StringValidationPresentContent.isNull;

public class AlternativeValidation extends RuntimeException{

    public static boolean fieldsContainsValue(String description,Question question) {

        try {
            if (isNull(description))
                throw new NullPointerException("Descrição da alternativa não pode ser nula");

            if (isBlankOrEmpty(description))
                throw new IllegalArgumentException("Descrição da alternativa  não pode ser vazia");



            if (question==null)
                throw new NullPointerException("Questão da alternativa  não pode ser nula");



            return true;

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }catch (NullPointerException e){
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
