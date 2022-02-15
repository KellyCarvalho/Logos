package avalon.category.validation;

import static commonValidation.StringValidationPresentContent.isBlankOrEmpty;
import static commonValidation.StringValidationPresentContent.isNull;

public class SubCategoryValidation extends RuntimeException {
    public static boolean isValidCode(String code) {


        try {
            boolean validationCode = code!=null? code.matches("[a-z0-9^-]+"):false;




            if (!validationCode)
                throw new IllegalArgumentException("Código da subcategoria não é válido, deve ter caracteres de a-z e algarismos de 0-9 e tudo bem se tiver o hífen em sua composição");

            return true;

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }



    }
    public static boolean fieldsContainsValue(String name, String code, String categoria) {

        try {
            if (isNull(name)) throw new NullPointerException("Nome da Subcategoria não pode ser nula");

            if (isBlankOrEmpty(name)) throw new IllegalArgumentException("Nome da Subcategoria não pode ser vazia ou em branco");

            if (isNull(code)) throw new NullPointerException("Código da Subcategoria não pode ser nulo");


            if (isBlankOrEmpty(code)) throw new IllegalArgumentException("Código da Subcategoria não pode ser vazia ou em branco");

            if (isNull(categoria)) throw new NullPointerException("Nome da categoria não pode ser nula");


            if (isBlankOrEmpty(categoria)) throw new IllegalArgumentException("Nome da Categoria não pode ser vazia ou em branco");





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
