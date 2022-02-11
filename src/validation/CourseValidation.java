package validation;

public class CourseValidation extends RuntimeException {


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

    public static boolean isValidEstimatedTime(int estimatedTime) {
        try {
            if (estimatedTime < 1) throw new IllegalArgumentException("Tempo estimado não pode ser menor que 1 hora");

            if (estimatedTime > 20) throw new IllegalArgumentException("Tempo estimado não pode ultrapassar 20 horas");


            return true;

        } catch (IllegalArgumentException e) {

            System.out.println(e.getMessage());

            return false;

        }
    }

    public static boolean fieldsContainsValue(String name, String code, String target, String instructor, String courseProgram, String skillDeveloped) {
        try {
            if (name.isBlank()||name.isEmpty())
                throw new IllegalArgumentException("Nome não pode ser vazio ");


            if (name == null)
                throw new NullPointerException("Nome não pode ser nulo ");

            if (code.isBlank()||code.isEmpty())
                throw new IllegalArgumentException("Code não pode ser vazio ");


            if (code == null)
                throw new NullPointerException("Code não pode ser nulo ");

            if (target.isBlank()||target.isEmpty())
                throw new IllegalArgumentException("Público Alvo não pode ser vazio ");


            if (target == null)
                throw new NullPointerException("Público Alvo não pode ser nulo ");

            if (instructor.isBlank()||instructor.isEmpty())
                throw new IllegalArgumentException("Nome do Instrutor não pode ser vazio ");

            if (instructor == null)
                throw new NullPointerException("Nome do Instrutor pode ser nulo ");

            if (courseProgram == "")
                throw new IllegalArgumentException("Ementa não pode ser vazia ");

            if (courseProgram == null)
                throw new NullPointerException("Ementa pode ser nula ");
            if (skillDeveloped == "")
                throw new IllegalArgumentException("Habilidades  desenvolvidas não podem estar vazias ");

            if (skillDeveloped == null)
                throw new NullPointerException("Habilidades  desenvolvidas não podem estar nulas ");


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
