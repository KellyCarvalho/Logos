import Logos.category.Category;
import Logos.course.Course;
import Logos.subCategory.SubCategory;

import java.io.IOException;
import java.util.List;

import static Logos.course.Course.*;
import static Logos.subCategory.SubCategory.getQuantitySubCategoriesActivesWithDescription;
import static Logos.utils.CsvReader.*;


public class Program {
    public static void main(String[] args) throws IOException {

        List<Category> categories = readCategories("files/planilha-dados-escola - Categoria.csv");
        List<SubCategory> subCategories = readSubCategories("files/planilha-dados-escola - Subcategoria.csv", categories);
        List<Course> courses = readCourses("files/planilha-dados-escola - Curso.csv", subCategories);

//        System.out.println(!categories.isEmpty() ? categories : "");
//        System.out.println(!subCategories.isEmpty() ? subCategories : "");
//        System.out.println(!courses.isEmpty() ? courses : "");
//
//        generateCategoryPage(courses, subCategories, categories);

        //System.out.println(isActive(categories)); ;
       // System.out.println(getCategoriesWithoutDescription(subCategories));


//        System.out.println(getCategoriesWithoutDescription(subCategories).isEmpty()?
//                "Não há categorias com descrição em branco ou vazia":getCategoriesWithoutDescription(subCategories));

//        System.out.println(isAnyPrivateCourse(courses));
//        System.out.println(getInstructorsListToCourses(courses));
//        System.out.println(getQuantitySubCategoriesActivesWithDescription(subCategories));
        System.out.println(getInstructorsWithCourseQuantities(courses));

    }
}
