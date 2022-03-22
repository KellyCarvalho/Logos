import Logos.category.CategoryDao;
import Logos.course.Course;
import Logos.course.CourseDao;
import Logos.section.Section;
import Logos.subCategory.SubCategory;
import Logos.subCategory.SubcategoryDao;

import javax.persistence.EntityManager;

import static Logos.utils.GenerateHtml.*;
import static Logos.utils.JPAUtil.getEntityManager;

public class Program {

    public static void main(String[] args) {

        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        CategoryDao categoryDao = new CategoryDao(entityManager);

        SubcategoryDao subcategoryDao = new SubcategoryDao(entityManager);

        SubCategory subCategory = subcategoryDao.getById(2);
        Course course = new Course("python", "nada", 10, "alana", subCategory);
        CourseDao courseDao = new CourseDao(entityManager);
        Section section = new Section("nada", "kl", course);

        System.out.println("Curso inserido: " + courseDao.insert(course));
        courseDao.delete("python11");

        System.out.println(courseDao.getAllPublic());
        courseDao.turnAllPublic();
        System.out.println(courseDao.getAllPublic());

        System.out.println(categoryDao.getAllActivesCategories());
        writeHtml(generateDataQueriesCoursePage(), showDataCategoryPage(), showDataSubCategoryPage());
       entityManager.getTransaction().commit();
        entityManager.close();


    }
}
