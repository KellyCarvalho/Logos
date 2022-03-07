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


    }
}
