package Logos.category.validation;

public class CategoryValidation {

    public static boolean isValidOrder(int order) {

        if (order < 0) throw new IllegalArgumentException("Ordem de Categoria não pode ser menor que 0");

        return true;
    }
}
