import Logos.category.Category;
import Logos.course.Course;
import Logos.subCategory.SubCategory;

import java.io.IOException;
import java.util.List;

import static Logos.utils.GenerateHtml.toGenerateHtml;
import static Logos.utils.CsvReader.toReadCsvTocourses;
import static Logos.utils.CsvReader.toReadCsvToSubCategories;
import static Logos.utils.CsvReader.readCsvCategories;


public class Program {
    public static void main(String[] args) throws IOException {

//        List<SubCategory> subCategories = toReadCsvToSubCategories("/home/kelly/Downloads/planilha-dados-escola - Subcategoria.csv", categories);
//        List<Course> courses = toReadCsvTocourses("/home/kelly/Downloads/planilha-dados-escola - Curso.csv", subCategories);
//        Collections.sort(categories, Comparator.comparing(Category::getOrder));
//        Collections.sort(subCategories, Comparator.comparing(SubCategory::getOrder));

//      System.out.println(categories);
//      System.out.println(subCategories);
//      System.out.println(courses);


       List<Category> categories = readCsvCategories("/home/kelly/Downloads/planilha-dados-escola - Categoria.csv");
                List<SubCategory> subCategories = toReadCsvToSubCategories("/home/kelly/Downloads/planilha-dados-escola - Subcategoria.csv", categories);
        List<Course> courses = toReadCsvTocourses("/home/kelly/Downloads/planilha-dados-escola - Curso.csv", subCategories);

        System.out.println(courses);

        toGenerateHtml(courses,subCategories,categories);





    }
}
