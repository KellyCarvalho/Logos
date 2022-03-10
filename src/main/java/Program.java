import Logos.category.Category;
import Logos.course.Course;
import Logos.subCategory.SubCategory;

import java.util.List;

import static Logos.course.CourseService.getSubCategoryName;
import static Logos.utils.CsvReader.*;
import static Logos.utils.GenerateSql.writeSql;

public class Program {
    public static void main(String[] args) {
       writeSql();
        List<Category> categories = readCategories("files/planilha-dados-escola - Categoria.csv");
        List<SubCategory> subCategories = readSubCategories("files/planilha-dados-escola - Subcategoria.csv",categories);
        List<Course> courses = readCourses("files/planilha-dados-escola - Curso.csv",subCategories);

        System.out.println(getSubCategoryName(courses));
        //TODO testar os validadores e o service de course
        //TODO Se der tempo testar


    }
}
