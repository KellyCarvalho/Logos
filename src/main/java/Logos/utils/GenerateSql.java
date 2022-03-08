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

    public static void insertCategories() {

        categories.forEach(category -> {
            String sql = """
                    INSERT INTO CATEGORY (`NAME`,`CODE`,`STUDY_GUIDE`,`DESCRIPTION`,`STATUS`,`ORDER`,`IMAGE_URL`,`COLOR_CODE`) 
                    VALUES('%s','%s','%s','%s','%s',%s,'%s','%s');
                    
                    """.formatted(category.getName(), category.getCode(), category.getStudyGuide(), category.getDescription()
                    , category.getStatus(), category.getOrder(), category.getImageUrl(), category.getColorCode());
            sb.append(sql);
            sb.append("");
        });
    }

    public static void insertSubCategories() {

        subCategories.forEach(subCategory -> {
            String sql = """
                    INSERT INTO SUBCATEGORY (`NAME`,`CODE`,`STUDY_GUIDE`,`DESCRIPTION`,`STATUS`,`ORDER`,`FK_CATEGORY`) 
                    VALUES('%s','%s','%s','%s','%s',%s,(SELECT `id` FROM category WHERE `code`='%s'));
                    
                    """.formatted(subCategory.getName(), subCategory.getCode(), subCategory.getStudyGuide(), subCategory.getDescription()
                    , subCategory.getStatus(), subCategory.getOrder(), subCategory.getCategoryCode());
            sb.append(sql);
            sb.append("");
        });
    }

    public static void insertCourses() {

        courses.forEach(course -> {
            String sql = """
                    INSERT INTO COURSE(`NAME`,`CODE` ,`ESTIMATED_TIME`,`VISIBILITY`,`TARGET_AUDIENCE`,`INSTRUCTOR_NAME`,`COURSE_PROGRAM_DESCRIPTION`,`DEVELOPED_SKILLS`,`FK_SUBCATEGORY`)
                    VALUES('%s','%s',%s,%s,'%s','%s','%s','%s',(SELECT `id` FROM `SUBCATEGORY` WHERE `CODE`='%s'));
                    
                    """.formatted(course.getName(), course.getCode(), course.getEstimatedTime(), course.isVisibility(), course.getTargetAudience(), course.getInstructorName(), course.getCourseProgramDescription(), course.getDevelopedSkills(), course.getSubCategoryCode());
            sb.append(sql);
        });
    }

    public static void writeSql() {
        insertCategories();
        insertSubCategories();
        insertCourses();
        try (PrintStream ps = new PrintStream(new File("files/loadData.sql"))) {
            ps.println(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
