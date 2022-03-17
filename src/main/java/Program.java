import Logos.course.CourseDTO;
import Logos.course.CourseDao;
import Logos.subCategory.SubcategoryDao;

import java.sql.SQLException;

import static Logos.utils.GenerateHtml.generateCoursePage;

public class Program {

    public static void main(String[] args) throws SQLException {
        generateCoursePage();

        //DAO
        CourseDao courseDao = new CourseDao();

        SubcategoryDao subcategoryDao = new SubcategoryDao();
        CourseDTO course = new CourseDTO("Golang", "golang2", 10, "Fábio");
        courseDao.insert(subcategoryDao.getSubCategoryById(1), course);

        courseDao.delete("golang2");
        courseDao.turnPublic();
    }
}
