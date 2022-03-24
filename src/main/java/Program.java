import Logos.category.CategoryDao;
import Logos.course.Course;
import Logos.course.CourseDao;
import Logos.subCategory.SubCategory;
import Logos.subCategory.SubcategoryDao;

import javax.persistence.EntityManager;

import static Logos.utils.GenerateHtml.writeHtml;
import static Logos.utils.GenerateSql.writeSql;
import static Logos.utils.JPAUtil.getEntityManager;

public class Program {

    public static void main(String[] args) {

        //Script para popular BD
        writeSql();
        EntityManager entityManager = getEntityManager("logos");
        entityManager.getTransaction().begin();

        CategoryDao categoryDao = new CategoryDao(entityManager);
        SubcategoryDao subcategoryDao = new SubcategoryDao(entityManager);
        CourseDao courseDao = new CourseDao(entityManager);

        //Objeto de inserção
        SubCategory subCategory = subcategoryDao.getByCode("java");
        Course course = new Course("Java oo", "javaoo", 10, "alana", subCategory);

        //INSERT
        System.out.println("Curso inserido: " + courseDao.insert(course));
        //DELETE
        courseDao.delete("javaoo");

        System.out.println("Cursos Públicos");
        courseDao.getAllPublic().forEach(publicCourse -> {
            System.out.println("Nome: " + publicCourse.getName());
        });

        //Tornar público
        courseDao.turnAllPublic();

        writeHtml(courseDao.getAllPublic(), subcategoryDao.getAllActiveSubcategories(), subcategoryDao.getSubcategoriesWithoutDescription(), categoryDao.getAllActiveCategories());

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
