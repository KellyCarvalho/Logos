import Logos.course.Course;
import Logos.course.CourseDTO;
import Logos.course.CourseDao;
import Logos.subCategory.SubCategory;
import Logos.subCategory.SubcategoryDao;

import java.sql.SQLException;

import static Logos.utils.GenerateHtml.generateCoursePage;
import static Logos.utils.GenerateSql.writeSql;

public class Program {

    public static void main(String[] args) {
        CourseDao courseDao = new CourseDao();
        writeSql();
        generateCoursePage();
        SubcategoryDao subcategoryDao = new SubcategoryDao();
        Course course = new Course("Golang", "golang14", 10, "Fábio", subcategoryDao.getById(1));
        courseDao.insert(course);
        courseDao.delete("golang13");
        courseDao.turnAllPublic();
    }
}
