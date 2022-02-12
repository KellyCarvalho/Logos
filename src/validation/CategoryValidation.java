package validation;

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
}
