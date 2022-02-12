package avalon.category.validation;

import static commonValidation.StringValidationPresentContent.isBlankOrEmpty;
import static commonValidation.StringValidationPresentContent.isNull;

public class SubCategoryValidation extends RuntimeException {
    public static boolean fieldsContainsValue(String name, String code) {

        try {
            if (isNull(name)) throw new NullPointerException("Nome da Subcategoria não pode ser nula");

            if (isBlankOrEmpty(name)) throw new IllegalArgumentException("Nome da Subcategoria não pode ser vazia ou em branco");

            if (isNull(code)) throw new NullPointerException("Código da Subcategoria não pode ser nulo");


            if (isBlankOrEmpty(code)) throw new IllegalArgumentException("Código da Subcategoria não pode ser vazia ou em branco");





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
