import avalon.activity.Video;
import avalon.category.Category;
import avalon.category.enums.CategoryStatus;
import avalon.course.Course;
import avalon.section.Section;
import avalon.subCategory.SubCategory;
import avalon.subCategory.enums.SubCategoryStatus;

public class Program {
    public static void main(String[] args) {


        //Testando instanciação Categoria
        Category category = new Category("Programação", "programacao", "Cursos de Programação", "Aprenda desde a lógica de programação a resolver problemas complexos", CategoryStatus.ACTIVE, 1, "alura.com.br", "#550000");
        System.out.println(category.toString());
        //Testando o caso do nome da categoria ser null
        //Category categoryTestingNullName = new Category(null,"programacao","Cursos de Programação","Aprenda desde a lógica de programação a resolver problemas complexos", CategoryStatus.ACTIVE,1,"alura.com.br","#550000");

        //Testando o caso do código da categoria ser null
        //Category categoryTestingNullCode = new Category("Programação",null,"Cursos de Programação","Aprenda desde a lógica de programação a resolver problemas complexos", CategoryStatus.ACTIVE,1,"alura.com.br","#550000");

        //Testando o caso do nome da categoria ser branco ou vazio
        //Category categoryTestingEmptyOrBlankName = new Category(" ","programacao","Cursos de Programação","Aprenda desde a lógica de programação a resolver problemas complexos", CategoryStatus.ACTIVE,1,"alura.com.br","#550000");

        //Testando o caso do código da categoria ser branco ou vazio
        //Category categoryTestingEmptyOrBlankCode = new Category("Programação", " ", "Cursos de Programação", "Aprenda desde a lógica de programação a resolver problemas complexos", CategoryStatus.ACTIVE, 1, "alura.com.br", "#550000");

        //Testando instanciação SubCategoria
        System.out.println();

        SubCategory subCategory = new SubCategory("Java", "java", "Cursos de java", "Aprenda desde a lógica de programação com Java", SubCategoryStatus.ACTIVE, 1, category);
        System.out.println(subCategory.toString());
        //Testando o caso do nome da subcategoria ser null

        //SubCategory subCategoryTestingNullName = new SubCategory(null, "java", "Cursos de java", "Aprenda desde a lógica de programação com Java", SubCategoryStatus.ACTIVE, 1, category);
        //Testando o caso do código da subcategoria ser null
        //SubCategory subCategoryTestingNullCode = new SubCategory("Java", null, "Cursos de java", "Aprenda desde a lógica de programação com Java", SubCategoryStatus.ACTIVE, 1, category);

        //Testando o caso do nome da subcategoria ser vazio
        //SubCategory subCategoryTestingEmptyOrBlankName = new SubCategory("", "java", "Cursos de java", "Aprenda desde a lógica de programação com Java", SubCategoryStatus.ACTIVE, 1, category);

        //Testando o caso do código da subcategoria ser vazio

        //SubCategory subCategoryTestingEmptyOrBlankCode = new SubCategory("java", "", "Cursos de java", "Aprenda desde a lógica de programação com Java", SubCategoryStatus.ACTIVE, 1, category);

        //Testando instanciação Course

        Course course = new Course("java", "j-01", 20, true, "Crianças", "Thais", "Java OO", "Java, POO", subCategory);

        System.out.println(course.toString());
        //Testando o caso do nome do curso ser null
        //Course courseTestingNullName = new Course(null, "j-01", 20, true, "Crianças", "Thais", "Java OO", "Java, POO", subCategory);
        //Testando o caso do Código do curso ser null
        //Course courseTestingNullCode = new Course("java", null, 20, true, "Crianças", "Thais", "Java OO", "Java, POO", subCategory);
        //Testando o caso do nome do instrutor do curso ser null
        //Course courseTestingNullInstructor = new Course("java", "j-01", 20, true, "Crianças", null, "Java OO", "Java, POO", subCategory);
        //Testando o caso da subcategoria ser null
        //Course courseTestingNullSubcategory = new Course("java", "j-01", 20, true, "Crianças", "Thais", "Java OO", "Java, POO", null);

        //Testando o caso do nome do curso ser vazio
        //Course courseTestingEmptyOrBlankName = new Course(" ", "j-01", 20, true, "Crianças", "Thais", "Java OO", "Java, POO", subCategory);
        //Testando o caso do código do curso ser vazio
        //Course courseTestingEmptyOrBlankCode = new Course(" ", "j-01", 20, true, "Crianças", "Thais", "Java OO", "Java, POO", subCategory);
        //Testando o caso do nome do instrutor ser vazio
        //Course courseTestingEmptyOrBlankInstructor = new Course("java", "j-01", 20, true, "Crianças", " ", "Java OO", "Java, POO", subCategory);

        Section section = new Section("cursos-java","java-course",1,true,true,course);
        System.out.println(section.toString());
        //Testando o caso do nome da seção ser nulll
        //Section sectionTestingNullName = new Section(null,"java-course",1,true,true,course);
        //Testando o caso do código da seção ser null
        //Section sectionTestingNullCode = new Section("cursos-java",null,1,true,true,course);
        //Testando o caso do curso da seção ser null
        //Section sectionTestingNullCourse = new Section("cursos-java","java-course",1,true,true,null);

        //Testando o caso do nome da seção ser vazia

        //Section sectionTestingEmptyOrBlankName = new Section(" ","java-course",1,true,true,course);

        //Testando o caso do código da seção ser vazia

        //Section sectionTestingEmptyOrBlankCode = new Section("cursos-java"," ",1,true,true,course);

        Video video = new Video("orientação a objetos em java","java-45",true,1,section,"java.com.br",39,"java é...");
        System.out.println();
        System.out.println(video.toString());

        //Testando o caso do título de atividade ser nulo
        //Video videoTestingNullTitle = new Video(null,"java-45",true,1,section,"java.com.br",39,"java é...");
        //Testando o caso do código de atividade ser nulo
        //Video videoTestingNullCode = new Video("orientação a objetos em java",null,true,1,section,"java.com.br",39,"java é...");

        //Testando o caso do título de atividade ser vazio
        //Video videoTestingEmptyOrBlankTitle = new Video("","java-45",true,1,section,"java.com.br",39,"java é...");
        //Testando o caso do Código  de atividade ser vazio
        //Video videoTestingEmptyOrBlankCode = new Video("orientação a objetos em java"," ",true,1,section,"java.com.br",39,"java é...");




    }
}
