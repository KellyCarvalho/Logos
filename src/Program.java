import entities.*;

public class Program {
    public static void main(String[] args) {


        Category category = new Category("Backend","backend-java","programação para backend","guia de estudos",true,1,"#FF0000");

        Category subCategory = new SubCategory("Backend","backend-java","programação para backend","guia de estudos",true,1,"#FF0000");

        Course course = new Course("java poo","java-17",20,"Ale", (SubCategory) subCategory);

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

        Explanation explanation = new Explanation("Porque java é diferente de javascript","jk530-ui",true,1,section,TypeActivity.EXPLANATION,"Explicação aqui");

        Video video = new Video("Porque java é diferente de javascript","jk530-ui",true,1,section,TypeActivity.VIDEO,"uiuoui.com.br",40,"java é legal");

        Question question  = new Question("Porque java é diferente de javascript","jk530-ui",true,1,section,TypeActivity.QUESTION, "Java é igual a javascript",TypeQuestion.MULTIPLE_CHOICE);

        Alternative alternative = new Alternative("Sim",1,false,"Não, javascript possui grandes diferenças com o Java",question);

        Alternative alternative2 = new Alternative("Não",1,true,"Está correto",question);

        System.out.println("Primeira Atividade");
        System.out.println();




    }
}
