package validation;

import entities.Course;

public class SectionValidation extends RuntimeException {

    public static boolean isValidCode(String code) {

        try {
            boolean validationName = code.matches("[a-z0-9^-]+");

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

    public static boolean fieldsContainsValue(String name, String code, Course course) {
        try {
            if (name.isBlank()||name.isEmpty())
                throw new IllegalArgumentException("Nome não pode ser vazio ");


            if (code.isBlank()||code.isEmpty())
                throw new NullPointerException("Nome não pode ser nulo ");

            if (code == "")
                throw new IllegalArgumentException("Código não pode ser vazio ");


            if (code == null)
                throw new NullPointerException("Código não pode ser nulo ");


            if (course == null)
                throw new NullPointerException("Curso não pode ser nulo");




            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean isValidOrder(int order){
        try {
            if (order<0)
                throw new IllegalArgumentException("Ordem não pode ter valor menor que 0");
            return true;
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return false;
        }


    }
}
