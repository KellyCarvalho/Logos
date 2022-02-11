import entities.*;

public class Program {
    public static void main(String[] args) {

        Course course = new Course(1L, "Lealdemais", "massa", 20, true, "crianças", "Ale", "aprender js", "OO em js");
        Section section = new Section(1L, "Atividades de Java", "javinha-90", 2, true, false, course);

        Activity activity = new Activity(1L, "Seja um ninja", "ninja", true, 1,section);
        Activity activity2 = new Activity(2L, "Java", "java-17", true, 1,section);

        Activity activity3 = new Activity(3L, "Como Trabalhar com Strings 3", "java-strings", true, 7,section);

        Explanation explanation = new Explanation(1L, "Pense numa descrição aqui", activity);

        Question question = new Question(1L, "Java é igual ao javascript?", activity2);

        Alternative alternative = new Alternative(1L, "Não", true, "Não, Os criadores do Javascript aproveitaram a ascenção do Java para popularizar a linguagem", question);

        Alternative alternative2 = new Alternative(2L, "Sim", false, "Sim são a mesma coisa", question);

        Video video = new Video(1L, "videodocurso69-", 30, "essa é a trancrição", activity3);

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

    }
}
