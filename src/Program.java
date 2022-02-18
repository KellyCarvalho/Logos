import Logos.activity.Explanation;
import Logos.activity.Question;
import Logos.activity.Video;
import Logos.activity.enums.TypeQuestion;
import Logos.alternative.Alternative;
import Logos.category.Category;
import Logos.category.enums.CategoryStatus;
import Logos.course.Course;
import Logos.section.Section;
import Logos.subCategory.SubCategory;
import Logos.subCategory.enums.SubCategoryStatus;

public class Program {
    public static void main(String[] args) {
        
        //Testando instanciação Categoria
        Category category = new Category("Programação", "programacao", "Cursos de Programação", "Aprenda desde a lógica de programação a resolver problemas complexos", CategoryStatus.ACTIVE, 1, "alura.com.br", "#550000");
        System.out.println(category.toString());
        //Testando o caso do nome da categoria ser null
       Category categoryTestingNullName = new Category(null,"programacao","Cursos de Programação","Aprenda desde a lógica de programação a resolver problemas complexos", CategoryStatus.ACTIVE,1,"alura.com.br","#550000");

        //Testando o caso do código da categoria ser null
       Category categoryTestingNullCode = new Category("Programação",null,"Cursos de Programação","Aprenda desde a lógica de programação a resolver problemas complexos", CategoryStatus.ACTIVE,1,"alura.com.br","#550000");

        //Testando o caso do nome da categoria ser branco ou vazio
        //Category categoryTestingEmptyOrBlankName = new Category(" ","programacao","Cursos de Programação","Aprenda desde a lógica de programação a resolver problemas complexos", CategoryStatus.ACTIVE,1,"alura.com.br","#550000");

        //Testando o caso do código da categoria ser branco ou vazio
        Category categoryTestingEmptyOrBlankCode = new Category("Programação", " ", "Cursos de Programação", "Aprenda desde a lógica de programação a resolver problemas complexos", CategoryStatus.ACTIVE, 1, "alura.com.br", "#550000");

        //Testando instanciação SubCategoria
        System.out.println();

        SubCategory subCategory = new SubCategory("Java", "java", "Cursos de java", "Aprenda desde a lógica de programação com Java", SubCategoryStatus.ACTIVE, 1, category);
        System.out.println(subCategory.toString());
        //Testando o caso do nome da subcategoria ser null

       SubCategory subCategoryTestingNullName = new SubCategory(null, "java", "Cursos de java", "Aprenda desde a lógica de programação com Java", SubCategoryStatus.ACTIVE, 1, category);
        //Testando o caso do código da subcategoria ser null
       SubCategory subCategoryTestingNullCode = new SubCategory("Java", null, "Cursos de java", "Aprenda desde a lógica de programação com Java", SubCategoryStatus.ACTIVE, 1, category);

        //Testando o caso do nome da subcategoria ser vazio
        SubCategory subCategoryTestingEmptyOrBlankName = new SubCategory("", "java", "Cursos de java", "Aprenda desde a lógica de programação com Java", SubCategoryStatus.ACTIVE, 1, category);

        //Testando o caso do código da subcategoria ser vazio

       SubCategory subCategoryTestingEmptyOrBlankCode = new SubCategory("java", "", "Cursos de java", "Aprenda desde a lógica de programação com Java", SubCategoryStatus.ACTIVE, 1, category);

        //Testando instanciação Course

        Course course = new Course("java", "j-01", 20, true, "Crianças", "Thais", "Java OO", "Java, POO", subCategory);

        System.out.println(course.toString());
        //Testando o caso do nome do curso ser null
       Course courseTestingNullName = new Course(null, "j-01", 20, true, "Crianças", "Thais", "Java OO", "Java, POO", subCategory);
        //Testando o caso do Código do curso ser null
     Course courseTestingNullCode = new Course("java", null, 20, true, "Crianças", "Thais", "Java OO", "Java, POO", subCategory);
        //Testando o caso do nome do instrutor do curso ser null
        Course courseTestingNullInstructor = new Course("java", "j-01", 20, true, "Crianças", null, "Java OO", "Java, POO", subCategory);
        //Testando o caso da subcategoria ser null
      Course courseTestingNullSubcategory = new Course("java", "j-01", 20, true, "Crianças", "Thais", "Java OO", "Java, POO", null);

        //Testando o caso do nome do curso ser vazio
       Course courseTestingEmptyOrBlankName = new Course(" ", "j-01", 20, true, "Crianças", "Thais", "Java OO", "Java, POO", subCategory);
        //Testando o caso do código do curso ser vazio
       Course courseTestingEmptyOrBlankCode = new Course(" ", "j-01", 20, true, "Crianças", "Thais", "Java OO", "Java, POO", subCategory);
        //Testando o caso do nome do instrutor ser vazio
       Course courseTestingEmptyOrBlankInstructor = new Course("java", "j-01", 20, true, "Crianças", " ", "Java OO", "Java, POO", subCategory);

        Section section = new Section("cursos-java", "java-course", 1, true, true, course);
        System.out.println(section.toString());
        //Testando o caso do nome da seção ser nulll
       Section sectionTestingNullName = new Section(null,"java-course",1,true,true,course);
        //Testando o caso do código da seção ser null
        //Section sectionTestingNullCode = new Section("cursos-java",null,1,true,true,course);
        //Testando o caso do curso da seção ser null
        //Section sectionTestingNullCourse = new Section("cursos-java","java-course",1,true,true,null);

        //Testando o caso do nome da seção ser vazia

        Section sectionTestingEmptyOrBlankName = new Section(" ","java-course",1,true,true,course);

        //Testando o caso do código da seção ser vazia

        Section sectionTestingEmptyOrBlankCode = new Section("cursos-java"," ",1,true,true,course);

        Video video = new Video("orientação a objetos em java", "java-45", true, 1, section, "java.com.br", 39, "java é...");
        System.out.println();
        System.out.println(video.toString());

        //Testando o caso do título de atividade ser nulo
        Video videoTestingNullTitle = new Video(null,"java-45",true,1,section,"java.com.br",39,"java é...");
        //Testando o caso do código de atividade ser nulo
        Video videoTestingNullCode = new Video("orientação a objetos em java",null,true,1,section,"java.com.br",39,"java é...");

        //Testando o caso do título de atividade ser vazio
        //Video videoTestingEmptyOrBlankTitle = new Video("","java-45",true,1,section,"java.com.br",39,"java é...");
        //Testando o caso do Código  de atividade ser vazio
        //Video videoTestingEmptyOrBlankCode = new Video("orientação a objetos em java"," ",true,1,section,"java.com.br",39,"java é...");
        System.out.println();
        Question question = new Question("O que é JDK é o mesmo que JRE", "jdk-89", true, 1, section, "Como funciona", TypeQuestion.TRUE_OR_FALSE);
        //Testando o caso de descrição da questão ser nula
        Question questionTestingNullDescription = new Question("O que é JDK é o mesmo que JRE", "jdk-89", true, 1, section, null, TypeQuestion.TRUE_OR_FALSE);

        //Testando o caso de tipo de questão  ser nula
        Question questionTestingNullTypeQuestion = new Question("O que é JDK é o mesmo que JRE", "jdk-89", true, 1, section, "Como funciona", null);
        //Testando o caso de descrição da questão ser vazia
        Question questionTestingEmptyOrBlankDescritption = new Question("O que é JDK é o mesmo que JRE", "jdk-89", true, 1, section, "", TypeQuestion.TRUE_OR_FALSE);

        Explanation explanation = new Explanation("está correto...", "ex-1", true, 1, section, "está correto...");
        System.out.println(explanation.toString());
        //Testando se explicação é nula
        Explanation explanationTestingNUllDescription = new Explanation("está correto...","ex-1",true,1,section,null);

        //Testando se explicação é vazia
        Explanation explanationTestingEmptyOrBlankDescription = new Explanation("está correto...","ex-1",true,1,section," ");

        //Testando demais regras de negócio
        //Duração de vídeo
        Video videoTestingDuration = new Video("orientação a objetos em java", "java-45", true, 1, section, "java.com.br", 0, "java é...");
        //Ordem
        Video videoTestingOrder = new Video("orientação a objetos em java", "java-45", true, -1, section, "java.com.br", 1, "java é...");
        Section sectionestingOrder = new Section("cursos-java", "java-course", -1, true, true, course);
        //Tempo estimado de curso
        Course courseTestingEstimatedTime = new Course("java", "j-01", 21, true, "Crianças", "Thais", "Java OO", "Java, POO", subCategory);
        Course courseTestingEstimatedTime2 = new Course("java", "j-01", -21, true, "Crianças", "Thais", "Java OO", "Java, POO", subCategory);

        Alternative alternative = new Alternative("Verdadeiro", 1, false, "Não", question);

        System.out.println(alternative.toString());
        //Testando descrição nula
        Alternative alternativeTestingNullDescription = new Alternative(null,1,true,"Sim",question);

        //Testando Questão nula
        Alternative alternativeTestingNullQuestion = new Alternative("ok", -1, true, "Sim", null);
        //Ordem
        Alternative alternativeTestingOrder = new Alternative(null, 1, true, "Sim", question);


        //test gitignore


    }
}
