import Logos.category.Category;
import Logos.course.Course;
import Logos.subCategory.SubCategory;

import java.io.IOException;
import java.util.List;

import static Logos.category.Category.getActiveCategories;
import static Logos.course.Course.*;
import static Logos.subCategory.SubCategory.*;
import static Logos.utils.CsvReader.*;


public class Program {
    public static void main(String[] args) {

        List<Category> categories = readCategories("files/planilha-dados-escola - Categoria.csv");
        List<SubCategory> subCategories = readSubCategories("files/planilha-dados-escola - Subcategoria.csv", categories);
        List<Course> courses = readCourses("files/planilha-dados-escola - Curso.csv", subCategories);

        System.out.println("Categorias ativas..........................");
        //Card 1
        System.out.println(getActiveCategories(categories));
        System.out.println("Subcategorias sem descrição.................");
        //Card 2
        System.out.println(getSubCategoriesWithoutDescription(subCategories));
        System.out.println("Quantidade de subcategorias ativas com descrição");
        //Card 3
        System.out.println(getQuantitySubCategoriesActivesWithDescription(subCategories));
        System.out.println("Existe algum curso privado");
        //Card 4
        System.out.println(hasAnyPrivateCourse(courses));
        System.out.println("Instrutores");
        //Card 5
        System.out.println(getInstructorFrom(courses));
        //Card 6
        System.out.println(getInstructorsWithCourseQuantities(courses));

    }
}
