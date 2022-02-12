package avalon.category.validation;



import static commonValidation.StringValidationPresentContent.isBlankOrEmpty;
import static commonValidation.StringValidationPresentContent.isNull;

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
            if (isNull(name)) throw new NullPointerException("Nome da Categoria não pode ser null");

            if (isBlankOrEmpty(name)) throw new IllegalArgumentException("Nome da Categoria não pode estar vazio ou em branco");

            if (isNull(code)) throw new NullPointerException("Código da Categoria não pode ser nulo");


            if (isBlankOrEmpty(code)) throw new IllegalArgumentException("Código da Categoria não pode ser vazio ou em branco");





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

