import Logos.category.Category;
import Logos.category.CategoryDao;
import Logos.course.Course;
import Logos.course.CourseDao;
import Logos.subCategory.SubCategory;
import Logos.subCategory.SubcategoryDao;

import javax.persistence.EntityManager;

import java.util.List;

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

        List<Course> publicCourses = courseDao.getAllPublic();
        List<SubCategory> activeSubcategories = subcategoryDao.getAllActiveSubcategories();
        List<String> subCategoriesWithoutDescription = subcategoryDao.getSubcategoriesWithoutDescription();
        List<Category> activeCategories = categoryDao.getAllActiveCategories();

        //Objeto de inserção
        SubCategory subCategory = subcategoryDao.getByCode("java");
        Course course = new Course("Java oo", "javaoo", 10, "alana", subCategory);

        //INSERT
        System.out.println("Curso inserido: " + courseDao.insert(course));
        //DELETE
        courseDao.delete("javaoo");

        //Tornar público
        courseDao.turnAllPublic();

        System.out.println("Cursos Públicos");
        publicCourses.forEach(publicCourse -> {
            System.out.println(publicCourse.getName());
        });
        System.out.println();
        System.out.println("SubCategorias Ativas");
        activeCategories.forEach(activeCategory -> {
            System.out.println(activeCategory.getName());
        });
        System.out.println();
        System.out.println("Nomes das SubCategorias sem descrição");
        System.out.println(subCategoriesWithoutDescription);
        System.out.println();
        System.out.println("Categorias Ativas");
        activeCategories.forEach(activeCategory->{
            System.out.println(activeCategory.getName());
        });

        writeHtml(publicCourses, activeSubcategories, subCategoriesWithoutDescription, activeCategories);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
