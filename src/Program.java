import entities.*;

public class Program {
    public static void main(String[] args) {

        Course course = new Course(1L, "Java POO", "java-poo", 20, true, "Iniciantes", "Camila", "Aqui você vai aprender o paradgma de orientação a objetos usando Java", "Java, tratamento de exceção e POO");
        Section section = new Section(1L, "java", "java-17", 2, true, false, course);

        Activity activity = new Activity(1L, "Código em Java", "java-90", true, 1, section);
        Activity activityWithQuestion = new Activity(1L, "Código em Java", "java-90", true, 1, section);
        Activity activityWithVideo = new Activity(1L, "Código em Java", "java-90", true, 1, section);
        Explanation explanation = new Explanation(1L, "isso ai",activity);


        Question question = new Question(1L, null, activityWithQuestion);

        Alternative alternative = new Alternative(1L, null, true, null, question);

        Alternative alternative2 = new Alternative(2L, null, false, null, question);

        Video video = new Video(1L, "uioureiure", 30, "kfdlkfldsklçf", activityWithVideo);


        System.out.println(video.getSection().getCode());

            /*



        System.out.println("Primeira Atividade");
        System.out.println("Título: " + activity.getTitle());
        System.out.println("Código: " + activity.getCode());
        System.out.println("Está Ativa? " + (activity.isActive() ? "Sim" : "Não"));
        System.out.println("Tipo: " + activity.getType().getExplanation().getDescription());

        System.out.println("Segunda Atividade");
        System.out.println("Título: " + activity2.getTitle());
        System.out.println("Código: " + activity2.getCode());
        System.out.println("Está Ativa? " + (activity2.isActive() ? "Sim" : "Não"));
        System.out.println("Tipo: " + activity2.getType().getQuestion().getStatement());
        System.out.println("Alternativa 1: " + alternative.getDescription());
        System.out.println("Alternativa 2: " + alternative2.getDescription());


*/

    }
}
