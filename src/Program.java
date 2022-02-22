import Logos.activity.Explanation;
import Logos.activity.Question;
import Logos.activity.Video;
import Logos.activity.enums.TypeQuestion;
import Logos.category.Category;
import Logos.course.Course;
import Logos.section.Section;
import Logos.subCategory.SubCategory;

public class Program {
    public static void main(String[] args) {

        //Testes Category
        //Testando instanciação Categoria
        Category category = new Category("Programação", "programacao");
        System.out.println(category.toString());
        //Cor da categoria cor que dá certo -> #363636 ou https://celke.com.br/artigo/tabela-de-cores-html-nome-hexadecimal-rgb
        Category categoryTestingColor = new Category("Programação", "programacao");
        //categoryTestingColor.setColorCode("#cccc78");
        //Testando o caso do nome da categoria ser null
        //Category categoryTestingNullName = new Category(null, "programacao");
        //Testando o caso do código da categoria ser null
        //Category categoryTestingNullCode = new Category("Programação", null);
        //Testando o caso do nome da categoria ser branco ou vazio
        //Category categoryTestingEmptyOrBlankName = new Category(" ","programacao");
        //Testando o caso do código da categoria ser branco ou vazio
        //Category categoryTestingEmptyOrBlankCode = new Category("Programação", " ");

        //Testes Subcategory
        //Testando instanciação
        System.out.println();
        SubCategory subCategory = new SubCategory("Java", "java", category);
        System.out.println(subCategory.toString());
        //Testando o caso do nome da subcategoria ser null
        //SubCategory subCategoryTestingNullName = new SubCategory(null, "java",  category);
        //Testando o caso do código da subcategoria ser null
        //SubCategory subCategoryTestingNullCode = new SubCategory("Java", null, category);
        //Testando o caso do nome da subcategoria ser vazio
        //SubCategory subCategoryTestingEmptyOrBlankName = new SubCategory("", "java",  category);
        //Testando o caso do código da subcategoria ser vazio
        //SubCategory subCategoryTestingEmptyOrBlankCode = new SubCategory("java", "", category);

        //Testes Course
        //Testando instanciação
        Course course = new Course("java", "j-01", 20, "Thais", subCategory);
        System.out.println(course.toString());
        //Testando o caso do nome do curso ser null
        //Course courseTestingNullName = new Course(null, "j-01", 20, "Thais", subCategory);
        //Testando o caso do Código do curso ser null
        //Course courseTestingNullCode = new Course("java", null, 20, "Thais",subCategory);
        //Testando o caso do nome do instrutor do curso ser null
        //Course courseTestingNullInstructor = new Course("java", "j-01", 20,  null,subCategory);
        //Testando o caso da subcategoria ser null
        //Course courseTestingNullSubcategory = new Course("java", "j-01", 20,  "Thais", null);
        //Testando o caso do nome do curso ser vazio
        //Course courseTestingEmptyOrBlankName = new Course(" ", "j-01", 20, "Thais",  subCategory);
        //Testando o caso do código do curso ser vazio
        //Course courseTestingEmptyOrBlankCode = new Course(" ", "j-01", 20, "Thais",  subCategory);
        //Testando o caso do nome do instrutor ser vazio
        //Course courseTestingEmptyOrBlankInstructor = new Course("java", "j-01", 20, " ",  subCategory);
        //Tempo estimado de curso
        //Course courseTestingEstimatedTime = new Course("java", "j-01", 0, "Thais",  subCategory);
        //Course courseTestingEstimatedTime2 = new Course("java", "j-01", 21, "Thais", subCategory);

        //Testes Section
        //Testando instanciação
        Section section = new Section("cursos-java", "java-course", course);
        System.out.println(section.toString());
        //Testando o caso do nome da seção ser null
        //Section sectionTestingNullName = new Section(null, "java-course",  course);
        //Testando o caso do código da seção ser null
        //Section sectionTestingNullCode = new Section("cursos-java", null, course);
        //Testando o caso do curso da seção ser null
        //Section sectionTestingNullCourse = new Section("cursos-java", "java-course", null);
        //Testando o caso do nome da seção ser vazia
        //Section sectionTestingEmptyOrBlankName = new Section(" ", "java-course",  course);
        //Testando o caso do código da seção ser vazia
        //Section sectionTestingEmptyOrBlankCode = new Section("cursos-java", " ",  course);

        //Testes Vídeo
        //Testando instanciação
        Video video = new Video("orientação a objetos em java", "java-45", section, "java.com.br");
        System.out.println();
        System.out.println(video.toString());
        //Testando o caso do título de atividade ser nulo
        //Video videoTestingNullTitle = new Video(null, "java-45", section, "java.com.br");
        //Testando o caso do código de atividade ser nulo
        //Video videoTestingNullCode = new Video("orientação a objetos em java", null, section, "java.com.br");
        //Testando o caso do título de atividade ser vazio
        //Video videoTestingEmptyOrBlankTitle = new Video("","java-45",section,"java.com.br");
        //Testando o caso do Código  de atividade ser vazio
        //Video videoTestingEmptyOrBlankCode = new Video("orientação a objetos em java"," ",section,"java.com.br");
        System.out.println();


        //Duração de vídeo

        //Teste Questão
        Question question = new Question("O que é JDK é o mesmo que JRE", "jdk-89", section, "Como funciona", TypeQuestion.TRUE_OR_FALSE);
        //Testando o caso de descrição da questão ser nula
        //Question questionTestingNullDescription = new Question("O que é JDK é o mesmo que JRE", "jdk-89", section, null, TypeQuestion.TRUE_OR_FALSE);
        //Testando o caso de tipo de questão  ser nula
        //Question questionTestingNullTypeQuestion = new Question("O que é JDK é o mesmo que JRE", "jdk-89", section, "Como funciona", null);
        //Testando o caso de descrição da questão ser vazia
        //Question questionTestingEmptyOrBlankDescritption = new Question("O que é JDK é o mesmo que JRE", "jdk-89", section, "", TypeQuestion.TRUE_OR_FALSE);

        //Teste Explanation
        Explanation explanation = new Explanation("está correto...", "ex-1", section, "está correto...");
        System.out.println(explanation.toString());
        //Testando se explicação é nula
        //Explanation explanationTestingNUllDescription = new Explanation(null, "ex-1",  section, null);
        //Testando se explicação é vazia
        //Explanation explanationTestingEmptyOrBlankDescription = new Explanation("", "ex-1", section, " ");
        //Testando demais regras de negócio
        //Testando descrição nula
        //Alternative alternativeTestingNullDescription = new Alternative(null,  true,  question);
        //Testando descrição vazia
        //Alternative alternativeTestingBlankOrEmptyDescription = new Alternative(" ",  true,  question);
        //Testando Questão nula
        //Alternative alternativeTestingNullQuestion = new Alternative("ok", true,  null);
    }
}
