import Logos.category.Category;
import Logos.course.Course;
import Logos.subCategory.SubCategory;

import java.io.IOException;
import java.util.List;

import static Logos.category.Category.isActive;
import static Logos.course.Course.*;
import static Logos.subCategory.SubCategory.*;
import static Logos.utils.CsvReader.*;
import static Logos.utils.GenerateHtml.generateCategoryPage;


public class Program {
    public static void main(String[] args) throws IOException {

        List<Category> categories = readCategories("files/planilha-dados-escola - Categoria.csv");
        List<SubCategory> subCategories = readSubCategories("files/planilha-dados-escola - Subcategoria.csv", categories);
        List<Course> courses = readCourses("files/planilha-dados-escola - Curso.csv", subCategories);
        
        //Card 1
        System.out.println(isActive(categories));
        //Card 2
        System.out.println(getCategoriesWithoutDescription(subCategories).isEmpty()?
                "Não há categorias com descrição em branco ou vazia":getCategoriesWithoutDescription(subCategories));
        System.out.println( getSubCategoriesWithoutDescription(subCategories));
        //Card 3
        System.out.println(getQuantitySubCategoriesActivesWithDescription(subCategories));
        //Card 4
        System.out.println(isAnyPrivateCourse(courses));
        //Card 5
        System.out.println(getInstructorsListToCourses(courses));
        //Card 6
        System.out.println(getInstructorsWithCourseQuantities(courses));

    }
}
