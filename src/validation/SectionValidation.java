package validation;

import entities.Course;

import java.util.ArrayList;
import java.util.List;

import static validation.StringValidationPresentContent.isBlankOrEmpty;
import static validation.StringValidationPresentContent.isNull;

public class SectionValidation extends RuntimeException {

    public static boolean isValidCode(String code) {


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

    public static boolean fieldsContainsValue(String name, String code, Course course) {



        try {
            if (isNull(name))
                throw new NullPointerException("Nome da seção não pode ser nulo ");


            if (isBlankOrEmpty(name))
                throw new IllegalArgumentException("Nome da seção não pode ser vazio ");

            if (isNull(code))
                throw new IllegalArgumentException("Código da seção não pode ser nulo");


            if (isBlankOrEmpty(code))
                throw new NullPointerException("Código da seção não pode ser vazio ");


            if (course == null)
                throw new NullPointerException("Curso da seção não pode ser nulo");


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
                throw new IllegalArgumentException("Ordem de seção não pode ter valor menor que 0");
            return true;
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return false;
        }


    }
}
