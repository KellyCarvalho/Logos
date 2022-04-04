package course;

import br.com.logos.category.Category;
import br.com.logos.course.Course;
import br.com.logos.course.CourseDao;
import br.com.logos.subCategory.SubCategory;
import br.com.logos.utils.builder.CategoryBuilder;
import br.com.logos.utils.builder.CourseBuilder;
import br.com.logos.utils.builder.SubCategoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static br.com.logos.utils.JPAUtil.getEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

public class CourseDaoTest {

    private CourseDao dao;
    private Category category;
    private SubCategory subCategory;
    private static EntityManager em;

    @BeforeEach
    void setUp() {
        em = getEntityManager("test");
        em.getTransaction().begin();
        this.dao = new CourseDao(em);

        category = new CategoryBuilder()
                .withName("Programação")
                .withCode("programacao")
                .withColorCode("#1C1C1C")
                .create();

        subCategory = new SubCategoryBuilder()
                .withName("Go")
                .withCode("go")
                .withCategory(category).create();

        em.persist(category);
        em.persist(subCategory);
    }

    @AfterEach
    void tearDown() {
        em.getTransaction().rollback();
        em.close();
    }

    @Test
    void getAllPublicShouldReturnAllPublicVisibilities() {
        Course courseWithVisibilityFalse1 = new CourseBuilder()
                .withName("Golang")
                .withCode("golang")
                .withEstimatedTime(10)
                .withInstructorName("Maria")
                .withSubCategory(subCategory).create();

        Course courseWithVisibilityTrue1 = new CourseBuilder()
                .withName("Java")
                .withCode("java")
                .withEstimatedTime(10)
                .withInstructorName("Camila")
                .withSubCategory(subCategory)
                .withVisibility(true)
                .withTargetAudience("iniciantes")
                .withDescription("java e OO")
                .withDevelopedSkills("Java")
                .create();

        Course courseWithVisibilityTrue2 = new CourseBuilder()
                .withName("PHP")
                .withCode("php")
                .withEstimatedTime(10)
                .withInstructorName("Suzani")
                .withSubCategory(subCategory)
                .withVisibility(true)
                .withTargetAudience("iniciantes")
                .withDescription("PHP e OO")
                .withDevelopedSkills("PHP")
                .create();

        dao.insert(courseWithVisibilityFalse1);
        dao.insert(courseWithVisibilityTrue1);
        dao.insert(courseWithVisibilityTrue2);

        assertThat(dao.getAllPublic())
                .hasSize(2)
                .extracting(Course::getCode)
                .containsExactly("java", "php")
                .doesNotContain("golang");
    }
}
