package avalon.alternative.validation;

public class AlternativeValidation extends RuntimeException{





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
