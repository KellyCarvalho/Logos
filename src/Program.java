import entities.*;
import validation.CourseValidation;

import java.util.Date;

public class Program {
    public static void main(String[] args) {


        Activity activity = new Activity(1L, "Seja um ninja", "ninja", true, 1);


        Explanation explanation = new Explanation(1L, "Pense numa descrição aqui", activity);

        Activity activity2 = new Activity(2L, "Java", "java-17", true, 1);
        Question question = new Question(1L, "Java é igual ao javascript", activity2);

        Alternative alternative = new Alternative(1L, "Não", true, "Não, Os criadores do Javascript aproveitaram a ascenção do Java para popularizar a linguagem", question);

        Alternative alternative2 = new Alternative(2L, "Sim", true, "Sim são a mesma coisa", question);



        Activity activity3 = new Activity(3L, "Como Trabalhar com Strings 3", "java-strings", true, 7);
        Video video = new Video(1L, "videodocurso", new Date(), "essa é a trancrição", activity3);




        Course course = new Course(1L,null,"#$$$%#",new Date(),true,"crianças","Ale","aprender js","OO em js");




    }
}
