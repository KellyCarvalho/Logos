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

        writeSql();
        EntityManager entityManager = getEntityManager("logos");
        entityManager.getTransaction().begin();

        CategoryDao categoryDao = new CategoryDao(entityManager);
        SubcategoryDao subcategoryDao = new SubcategoryDao(entityManager);
        CourseDao courseDao = new CourseDao(entityManager);

        //Objeto de inserção
        SubCategory subCategory = subcategoryDao.getByCode("java");
        Course course = new Course("Python", "python", 10, "alana", subCategory);

        //INSERT
        System.out.println("Curso inserido: " + courseDao.insert(course));
        //DELETE
        courseDao.delete("python");

        System.out.println("Cursos Públicos");
        courseDao.getAllPublic().forEach(publicCourse -> {
            System.out.println("Nome: " + publicCourse.getName());
        });

        //TORNAR PÚBLICO
        courseDao.turnAllPublic();

        courseDao.getAllPublic().forEach(publicCourse -> {
            System.out.println("Curso Público: " + publicCourse.getName());
        });

        categoryDao.getAllActiveCategories().forEach(category -> {
            System.out.println("Categoria Ativa: " + category.getName());
        });

        subcategoryDao.getAllActiveSubcategories().forEach(activesubCategory -> {
            System.out.println(activesubCategory.getName());
        });

        subcategoryDao.getSubcategoriesWithoutDescription().forEach(subcategoryName -> {
                    System.out.println("Subcategoria sem descrição: " + subcategoryName);
                }
        );

        writeHtml(courseDao.getAllPublic(), subcategoryDao.getAllActiveSubcategories(), subcategoryDao.getSubcategoriesWithoutDescription(), categoryDao.getAllActiveCategories());

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
