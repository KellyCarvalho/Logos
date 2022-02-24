import Logos.category.Category;
import Logos.course.Course;
import Logos.subCategory.SubCategory;

import java.io.IOException;
import java.util.List;

import static Logos.utils.GenerateHtml.toGenerateHtml;
import static Logos.utils.CsvReader.readCsvCourses;
import static Logos.utils.CsvReader.readCsvSubCategories;
import static Logos.utils.CsvReader.readCsvCategories;


public class Program {
    public static void main(String[] args) throws IOException {

        List<Category> categories = readCsvCategories("/home/kelly/Downloads/planilha-dados-escola - Categoria.csv");
        List<SubCategory> subCategories = readCsvSubCategories("/home/kelly/Downloads/planilha-dados-escola - Subcategoria.csv", categories);
        List<Course> courses = readCsvCourses("/home/kelly/Downloads/planilha-dados-escola - Curso.csv", subCategories);

        System.out.println(categories);
        System.out.println(subCategories);
        System.out.println(courses);

        toGenerateHtml(courses, subCategories, categories);


    }
}
