package Logos.utils;

import Logos.category.Category;
import Logos.course.Course;
import Logos.subCategory.SubCategory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

import static Logos.utils.CsvReader.*;

public class GenerateSql {

    private static List<Category> categories = readCategories("files/planilha-dados-escola - Categoria.csv");
    private static List<SubCategory> subCategories = readSubCategories("files/planilha-dados-escola - Subcategoria.csv", categories);
    private static List<Course> courses = readCourses("files/planilha-dados-escola - Curso.csv", subCategories);
    private static StringBuilder sb = new StringBuilder();

    public static void writeInsertCategories() {
        categories.forEach(category -> {
            String sql = """
                    INSERT INTO Category (`name`,`identifier_code`,`position`,`category_description`,`status`,`image_url`,`color_code`) 
                    VALUES("%s","%s",%d,"%s","%s","%s","%s");                                
                    """.formatted(category.getName(), category.getCode(), category.getOrder(), category.getDescription()
                    , category.getStatus(), category.getImageUrl(), category.getColorCode());
            sb.append(sql);
        });
    }

    public static void writeInsertSubCategories() {
        subCategories.forEach(subCategory -> {
            String sql = """
                    INSERT INTO Subcategory (`name`,`identifier_code`,`position`,`subcategory_description`,`status`,`fk_category`) 
                    VALUES("%s","%s",%d,"%s","%s",(SELECT `id` FROM Category WHERE `identifier_code`="%s"));                               
                    """.formatted(subCategory.getName(), subCategory.getCode(), subCategory.getOrder(), subCategory.getDescription()
                    , subCategory.getStatus(), subCategory.getCategoryCode());
            sb.append(sql);
        });
    }

    public static void writeInsertCourses() {
        courses.forEach(course -> {
            String sql = """
                    INSERT INTO Course(`name`,`identifier_code`,`estimated_time`,`visibility`,`target_audience`,`instructor_name`,`course_program_description`,`developed_skills`,`fk_subcategory`)
                    VALUES("%s"," %s ",%s,%s," %s "," %s "," %s "," %s ",(SELECT `id` FROM `Subcategory` WHERE `identifier_code`="%s"));
                                        
                    """.formatted(course.getName(), course.getCode(), course.getEstimatedTime(), course.isVisibility(),
                    course.getTargetAudience(), course.getInstructorName(), course.getCourseProgramDescription(), course.getDevelopedSkills(), course.getSubCategoryCode());
            sb.append(sql);
        });
    }

    public static void writeSql() {
        writeInsertCategories();
        writeInsertSubCategories();
        writeInsertCourses();
        try (PrintStream ps = new PrintStream(new File("files/database/loadData.sql"))) {
            ps.println(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
