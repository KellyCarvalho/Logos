import entities.*;

public class Program {
    public static void main(String[] args) {



        Course course = new Course("java poo","java-17",20,"Ale");

        Section section = new Section("Conteúdo de Java", "java-17",course);

        System.out.println("Informações do curso");
        System.out.println("Nome: "+course.getName());
        System.out.println("Código: "+course.getCode());
        System.out.println("Tempo estimado: "+course.getEstimatedTime());
        System.out.println("Visibilidade: "+(course.isVisibility()?"Público":"Privado"));
        System.out.println("Público Alvo: "+course.getTarget());
        System.out.println("Instrutor: "+course.getInstructor());
        System.out.println("Ementa: "+course.getCourseProgram());
        System.out.println("Skills desenvolvidas: "+course.getSkillsDeveloped());

        Explanation explanation = new Explanation("Porque java é diferente de javascript","jk530-ui",true,1,section,"Imagine uma explicação aqui");

        Video video = new Video("Porque java é diferente de javascript","jk530-ui",true,1,section,"uiuoui.com.br",40,"java é legal");

        Question question  = new Question("Porque java é diferente de javascript","jk530-ui",true,1,section, "Java é igual a javascript",TypeQuestion.MULTIPLE_CHOICE);

        Alternative alternative = new Alternative("Sim",1,false,"Não, javascript possui grandes diferenças com o Java",question);

        Category category =new Category("backend","back","Aqui é back","aqui é um guia",true,1,"#000000");




    }
}
