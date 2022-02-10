package validation;

public class CourseValidation extends RuntimeException {


    public static boolean toValidCode(String code) {

        try {
            boolean validationName = code.matches("[a-z]*[0-9]*-*");

            if (code == "") throw new IllegalArgumentException("Código não pode ser vazio");

            if (code == null) throw new NullPointerException("Código não pode ser nulo");

            if (!validationName)
                throw new IllegalArgumentException("Código não é válido, deve ter caracteres de a-z e algarismos de 0-9 e tudo bem se tiver o hífen em sua composição");
            return true;

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return false;
        }




    }

    public static boolean toValidateName(String name) {

    try {
        if (name == "") throw new IllegalArgumentException("Nome não pode ser vazio");

        if (name == null) throw new NullPointerException("Nome não pode ser nulo");
        return true;

    }catch (IllegalArgumentException e){
        System.out.println(e.getMessage());
        return false;
    }catch (NullPointerException e){
        System.out.println(e.getMessage());
        return false;
    }








    }
}
