package Logos.activity.validation;

public class ActivityValidation {

    public static boolean isValidOrder(int order) {
        if (order < 0)
            throw new IllegalArgumentException("Ordem não pode ter valor menor que 0");
        return true;
    }
}
