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
        Category category = new Category("Programação", "programacao");
        System.out.println(category.toString());
        //Testando o caso do nome da categoria ser null
        //Category categoryTestingNullName = new Category(null, "programacao");

        //Testando o caso do código da categoria ser null
        //Category categoryTestingNullCode = new Category("Programação", null);

        //Testando o caso do nome da categoria ser branco ou vazio
        //Category categoryTestingEmptyOrBlankName = new Category(" ","programacao");

        //Testando o caso do código da categoria ser branco ou vazio
        //Category categoryTestingEmptyOrBlankCode = new Category("Programação", " ");

        //Testando instanciação SubCategoria
        System.out.println();

        SubCategory subCategory = new SubCategory("Java", "java",  category);
        System.out.println(subCategory.toString());
        //Testando o caso do nome da subcategoria ser null

        //SubCategory subCategoryTestingNullName = new SubCategory(null, "java",  category);
        //Testando o caso do código da subcategoria ser null
        //SubCategory subCategoryTestingNullCode = new SubCategory("Java", null, category);

        //Testando o caso do nome da subcategoria ser vazio
        //SubCategory subCategoryTestingEmptyOrBlankName = new SubCategory("", "java",  category);

        //Testando o caso do código da subcategoria ser vazio

        //SubCategory subCategoryTestingEmptyOrBlankCode = new SubCategory("java", "", category);

        //Testando instanciação Course

        Course course = new Course("java", "j-01", 20,  "Thais",  subCategory);

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
        Question question = new Question("O que é JDK é o mesmo que JRE", "jdk-89", section, "Como funciona", TypeQuestion.TRUE_OR_FALSE);
        //Testando o caso de descrição da questão ser nula
        //Question questionTestingNullDescription = new Question("O que é JDK é o mesmo que JRE", "jdk-89", section, null, TypeQuestion.TRUE_OR_FALSE);

        //Testando o caso de tipo de questão  ser nula
        //Question questionTestingNullTypeQuestion = new Question("O que é JDK é o mesmo que JRE", "jdk-89", section, "Como funciona", null);
        //Testando o caso de descrição da questão ser vazia
        //Question questionTestingEmptyOrBlankDescritption = new Question("O que é JDK é o mesmo que JRE", "jdk-89", section, "", TypeQuestion.TRUE_OR_FALSE);

        Explanation explanation = new Explanation("está correto...", "ex-1", section, "está correto...");
        System.out.println(explanation.toString());
        //Testando se explicação é nula
        //Explanation explanationTestingNUllDescription = new Explanation(null, "ex-1",  section, null);

        //Testando se explicação é vazia
        //Explanation explanationTestingEmptyOrBlankDescription = new Explanation("", "ex-1", section, " ");

        //Testando demais regras de negócio
        //Duração de vídeo
        Video videoTestingDuration = new Video("orientação a objetos em java", "java-45", section, "java.com.br");

        //videoTestingDuration.setDurationInMinutes(0);

        //Ordem
        Video videoTestingOrder = new Video("orientação a objetos em java", "java-45", section, "java.com.br");
        //videoTestingOrder.setOrder(-1);
        Section sectionestingOrder = new Section("cursos-java", "java-course", course);
        //sectionestingOrder.setOrder(0);


        //Tempo estimado de curso
        //Course courseTestingEstimatedTime = new Course("java", "j-01", 21, "Thais",  subCategory);
        //Course courseTestingEstimatedTime2 = new Course("java", "j-01", -21, "Thais", subCategory);

        Alternative alternative = new Alternative("Verdadeiro", false, question);

        System.out.println(alternative.toString());
        //Testando descrição nula
        //Alternative alternativeTestingNullDescription = new Alternative(null,  true,  question);

        //Testando descrição vazia
        //Alternative alternativeTestingBlankOrEmptyDescription = new Alternative(" ",  true,  question);

        //Testando Questão nula
        //Alternative alternativeTestingNullQuestion = new Alternative("ok", true,  null);
        //Ordem
        Alternative alternativeTestingOrder = new Alternative("ok",  true,  question);
        //alternativeTestingOrder.setOrder(-1);


    }
}
