import Logos.category.Category;
import Logos.category.CategoryDao;
import Logos.course.Course;
import Logos.course.CourseDao;
import Logos.subCategory.SubCategory;
import Logos.subCategory.SubcategoryDao;

import javax.persistence.EntityManager;

import java.util.List;

import static Logos.utils.GenerateHtml.*;
import static Logos.utils.JPAUtil.getEntityManager;

public class Program {

    public static void main(String[] args) {

        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        CategoryDao categoryDao = new CategoryDao(entityManager);

        SubcategoryDao subcategoryDao = new SubcategoryDao(entityManager);
//
//        SubCategory subCategory = subcategoryDao.getById(2);
//        Course course = new Course("python", "public", 10, "alana", subCategory);
//        CourseDao courseDao = new CourseDao(entityManager);
//
//       // System.out.println(courseDao.insert(course));
//        //courseDao.delete("python11");
//
//        System.out.println(courseDao.getAllPublic());
//        courseDao.turnAllPublic();
        List<Category> categories = categoryDao.getAllCategoriesActives();
        System.out.println(categories);

        writeHtml(generateDataQueriesCoursePage(), generateDataQueriesCategoryPage(),generateDataQueriesSubCategoryPage() );
//       entityManager.getTransaction().commit();
//        entityManager.close();


    }
}
