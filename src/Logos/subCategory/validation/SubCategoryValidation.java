package Logos.subCategory.validation;

public class SubCategoryValidation {
    public static boolean isValidOrder(int order) {

        if (order < 0) throw new IllegalArgumentException("Ordem de SubCategoria não pode ser menor que 0");

        return true;
    }
}
