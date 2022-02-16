import avalon.category.Category;
import avalon.category.enums.CategoryStatus;
import avalon.subCategory.SubCategory;
import avalon.subCategory.enums.SubCategoryStatus;

public class Program {
    public static void main(String[] args) {


        Category category = new Category("Programação","programacao","Cursos de Programação","Aprenda desde a lógica de programação a resolver problemas complexos", CategoryStatus.ACTIVE,1,"alura.com.br","#550000");
        SubCategory subCategory = new SubCategory("Java","java","Cursos de java","Aprenda desde a lógica de programação com Java", SubCategoryStatus.ACTIVE,1,category);

        System.out.println(subCategory.toString());

    }
}
