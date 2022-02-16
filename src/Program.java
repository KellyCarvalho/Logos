import avalon.category.Category;
import avalon.category.enums.CategoryStatus;
import avalon.course.Course;
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



    }
}
