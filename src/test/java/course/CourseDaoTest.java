package course;

import Logos.category.Category;
import Logos.course.Course;
import Logos.course.CourseDao;
import Logos.subCategory.SubCategory;
import Logos.utils.builder.CategoryBuilder;
import Logos.utils.builder.CourseBuilder;
import Logos.utils.builder.SubCategoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static Logos.utils.JPAUtil.getEntityManagerTest;
import static org.assertj.core.api.Assertions.assertThat;

public class CourseDaoTest {

    private CourseDao dao;
    EntityManager em = getEntityManagerTest();

    @BeforeEach
    void setUp() {
        em.getTransaction().begin();
        this.dao = new CourseDao(em);
        Category category = new CategoryBuilder()
                .withName("Programação")
                .withCode("programacao")
                .withColorCode("#1C1C1C")
                .create();
        SubCategory subCategory = new SubCategoryBuilder()
                .withName("Go")
                .withCode("go")
                .withCategory(category).create();
        Course courseGolang = new CourseBuilder()
                .withName("Golang")
                .withCode("golang")
                .withEstimatedTime(10)
                .withInstructorName("Maria")
                .withSubCategory(subCategory).create();
        Course courseJava = new CourseBuilder()
                .withName("Java")
                .withCode("java")
                .withEstimatedTime(10)
                .withInstructorName("Camila")
                .withSubCategory(subCategory)
                .withVisibility(true)
                .withTargetAudience("iniciantes")
                .withCourseProgramDescription("java e OO")
                .withDevelopedSkills("Java")
                .create();

        Course coursePhp = new CourseBuilder()
                .withName("PHP")
                .withCode("php")
                .withEstimatedTime(10)
                .withInstructorName("Suzani")
                .withSubCategory(subCategory)
                .withVisibility(true)
                .withTargetAudience("iniciantes")
                .withCourseProgramDescription("PHP e OO")
                .withDevelopedSkills("PHP")
                .create();

        em.persist(category);
        em.persist(subCategory);
        dao.insert(courseGolang);
        dao.insert(courseJava);
        dao.insert(coursePhp);
    }

    @Test
    void getAllPublicShouldReturnAllPublicVisibilities() {
        List<Course> allPublicCourses = dao.getAllPublic();
        assertThat(allPublicCourses)
                .hasSize(2)
                .extracting(Course::getCode)
                .containsExactly("java", "php");
        em.close();
    }
}
