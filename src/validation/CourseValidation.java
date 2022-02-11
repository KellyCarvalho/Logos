package validation;

import static validation.StringValidationPresentContent.isBlankOrEmpty;
import static validation.StringValidationPresentContent.isNull;

public class CourseValidation extends RuntimeException {


    public static boolean isValidCode(String code) {

        try {
            boolean validationCode = code!=null? code.matches("[a-z0-9^-]+"):false;


            if (!validationCode)
                throw new IllegalArgumentException("Código do curso não é válido, deve ter caracteres de a-z e algarismos de 0-9 e tudo bem se tiver o hífen em sua composição");
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

            if (isNull(name))
                throw new NullPointerException("Nome do Curso não pode ser nulo ");

            if (isBlankOrEmpty(name))
                throw new IllegalArgumentException("Nome do Curso não pode ser vazio ");


            if (isNull(code))
                throw new NullPointerException("Código do curso não pode ser nulo ");

            if (isBlankOrEmpty(code))
                throw new IllegalArgumentException("Código do curso não pode ser vazio ");


            if (isNull(target))
                throw new NullPointerException("Público Alvo do curso não pode ser nulo ");

            if (isBlankOrEmpty(target))
                throw new IllegalArgumentException("Público Alvo do curso não pode ser vazio ");

            if (isNull(instructor))
                throw new NullPointerException("Nome do Instrutor  do curso não pode ser nulo ");


            if (isBlankOrEmpty(instructor))
                throw new IllegalArgumentException("Nome do Instrutor do curso não pode ser vazio ");

            if (isNull(courseProgram))
                throw new NullPointerException("Ementa do curso não pode ser nula ");

            if (isBlankOrEmpty(courseProgram))
                throw new IllegalArgumentException("Ementa do curso não pode ser vazia ");

            if (isNull(skillDeveloped))
                throw new NullPointerException("Habilidades  desenvolvidas do curso não pode ser um campo nulo ");


            if (isBlankOrEmpty(skillDeveloped))
                throw new IllegalArgumentException("Habilidades  desenvolvidas do curso não pode ser um campo vazio ");

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
