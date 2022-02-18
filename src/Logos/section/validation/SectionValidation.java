package Logos.section.validation;

public class SectionValidation extends RuntimeException {

    public static boolean isValidOrder(int order) {
        if (order < 0) throw new IllegalArgumentException("Ordem de seção não pode ter valor menor que 0");
        return true;
    }
}
