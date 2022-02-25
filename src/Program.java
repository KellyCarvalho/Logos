import Logos.category.Category;
import Logos.course.Course;
import Logos.subCategory.SubCategory;

import java.io.IOException;
import java.util.List;

import static Logos.utils.CsvReader.*;
import static Logos.utils.GenerateHtml.generateCategoryPage;


public class Program {
    public static void main(String[] args) throws IOException {

        List<Category> categories = readCategories("files/planilha-dados-escola - Categoria.csv");
        List<SubCategory> subCategories = readSubCategories("files/planilha-dados-escola - Subcategoria.csv", categories);
        List<Course> courses = readCourses("files/planilha-dados-escola - Curso.csv", subCategories);

        System.out.println(!categories.isEmpty() ? categories : "");
        System.out.println(!subCategories.isEmpty() ? subCategories : "");
        System.out.println(!courses.isEmpty() ? courses : "");

        generateCategoryPage(courses, subCategories, categories);
    }
}
