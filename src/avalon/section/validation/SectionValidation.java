package avalon.section.validation;

public class SectionValidation extends RuntimeException {

    public static boolean isValidCode(String code) {

//TODO arrumar espaçamento e tirar try catch
        try {
            boolean validationCode = code!=null? code.matches("[a-z0-9^-]+"):false;
   if (!validationCode)
       throw new IllegalArgumentException("Código da seção não é válido, deve ter caracteres de a-z e algarismos de 0-9 e tudo bem se tiver o hífen em sua composição");

            return true;

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }



    }

    //TODO trocar exception null pointer para illegal argument


    public static boolean isValidOrder(int order) {
        if (order < 0)
            throw new IllegalArgumentException("Ordem de seção não pode ter valor menor que 0");
        return true;
    }
}
