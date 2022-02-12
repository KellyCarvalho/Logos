package commonValidation;

public class StringValidationPresentContent {

    public static boolean isBlankOrEmpty(String text) {


        if (text.isBlank()||text.isEmpty())return true;



            return false;



    }

    public static boolean isNull(String text) {


            if (text==null) return true;


            return false;




    }


}
