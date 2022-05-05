package br.com.logos.course;

import br.com.logos.category.Category;
import br.com.logos.category.enums.CategoryStatus;
import br.com.logos.subCategory.SubCategory;
import br.com.logos.subCategory.enums.SubCategoryStatus;
import br.com.logos.utils.builders.CategoryBuilder;
import br.com.logos.utils.builders.CourseBuilder;
import br.com.logos.utils.builders.SubCategoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CourseRepositoryTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void getAllCoursesCountByCategoryShouldReturnCategoryNameAndQuantityCourses() {
        Category activeCategory = new CategoryBuilder()
                .withName("Programação")
                .withCode("programacao")
                .withStatus(CategoryStatus.ACTIVE)
                .withOrder(0)
                .withColorCode("#00c86f")
                .create();

        Category disabledCategory = new CategoryBuilder()
                .withName("DevOps")
                .withCode("devops")
                .withOrder(2)
                .withColorCode("#f16165")
                .create();

        SubCategory activeSubCategoryZero = new SubCategoryBuilder()
                .withCode("java")
                .withName("Java")
                .withStatus(SubCategoryStatus.ACTIVE)
                .withOrder(0)
                .withCategory(activeCategory)
                .create();

        SubCategory activeSubCategoryOne = new SubCategoryBuilder()
                .withCode("builds-e-controle-de-versao")
                .withName("Builds e Controle de versão")
                .withStatus(SubCategoryStatus.ACTIVE)
                .withCategory(disabledCategory)
                .create();

        SubCategory disabledSubCategory = new SubCategoryBuilder()
                .withCode("java-persistence")
                .withName("Java persistence")
                .withStatus(SubCategoryStatus.ACTIVE)
                .withOrder(1)
                .withCategory(activeCategory)
                .create();

        Course visibleCourseZero = new CourseBuilder().withCode("java-oo")
                .withName("Java OO: Introdução à Orientação a Objetos")
                .withEstimatedTime(8)
                .withInstructorName("Paulo Silveira")
                .withVisibility(true)
                .withSubCategory(activeSubCategoryZero)
                .create();

        Course visibleCourseOne = new CourseBuilder()
                .withCode("jpa-iniciante")
                .withName("Jpa Para iniciantes")
                .withEstimatedTime(8)
                .withInstructorName("Paulo Silveira")
                .withVisibility(true)
                .withSubCategory(disabledSubCategory)
                .create();

        Course visibleCourseTwo = new CourseBuilder()
                .withCode("git-e-github")
                .withName("Git e Github")
                .withEstimatedTime(9)
                .withInstructorName("Thais")
                .withVisibility(true)
                .withSubCategory(activeSubCategoryOne)
                .create();

        Course noVisibleCourseZero = new CourseBuilder().withCode("jpa")
                .withName("Jpa")
                .withEstimatedTime(8)
                .withInstructorName("Paulo Silveira")
                .withVisibility(false)
                .withSubCategory(disabledSubCategory)
                .create();

        em.persist(activeCategory);
        em.persist(disabledCategory);
        em.persist(activeSubCategoryZero);
        em.persist(activeSubCategoryOne);
        em.persist(disabledSubCategory);
        em.persist(visibleCourseZero);
        em.persist(visibleCourseOne);
        em.persist(visibleCourseTwo);
        em.persist(noVisibleCourseZero);

        List<CourseByCategoryProjection> coursesByCategoryProjection = courseRepository.getAllCoursesCountByCategory();

        assertThat(coursesByCategoryProjection).hasSize(2).extracting(CourseByCategoryProjection::getQuantity).containsExactly(3, 1);
        assertThat(coursesByCategoryProjection).hasSize(2).extracting(CourseByCategoryProjection::getCategoryName).containsExactly("Programação", "DevOps");
    }

    @Test
    public void getInstructorWithMoreCoursesShouldReturnTheInstructorNameWithMoreCourses() {
        Category category = new CategoryBuilder()
                .withName("Programação")
                .withCode("programacao")
                .withStatus(CategoryStatus.ACTIVE)
                .withOrder(0)
                .withColorCode("#00c86f")
                .create();

        SubCategory subCategory = new SubCategoryBuilder()
                .withCode("java")
                .withName("Java")
                .withStatus(SubCategoryStatus.ACTIVE)
                .withOrder(0)
                .withCategory(category)
                .create();

        Course visibleCourseZero = new CourseBuilder()
                .withCode("java-oo")
                .withName("Java OO: Introdução à Orientação a Objetos")
                .withEstimatedTime(8)
                .withInstructorName("Paulo Silveira")
                .withVisibility(true)
                .withSubCategory(subCategory)
                .create();

        Course visibleCourseOne = new CourseBuilder()
                .withCode("java-iniciante")
                .withName("Java Iniciante")
                .withEstimatedTime(9)
                .withInstructorName("Pedro")
                .withVisibility(true)
                .withSubCategory(subCategory)
                .create();

        Course visibleCourseTwo = new CourseBuilder()
                .withCode("java-estrutura-dados")
                .withName("Java Estrutura de dados")
                .withEstimatedTime(9)
                .withInstructorName("Pedro")
                .withVisibility(true)
                .withSubCategory(subCategory)
                .create();

        Course noVisibleCourseZero = new CourseBuilder()
                .withCode("jpa")
                .withName("Jpa")
                .withEstimatedTime(8)
                .withInstructorName("Paulo Silveira")
                .withSubCategory(subCategory)
                .create();

        Course noVisibleCourseOne = new CourseBuilder()
                .withCode("logica-programacao-java")
                .withName("Lógica de programação com Java")
                .withEstimatedTime(9)
                .withInstructorName("Camila")
                .withSubCategory(subCategory)
                .create();

        em.persist(category);
        em.persist(subCategory);
        em.persist(visibleCourseZero);
        em.persist(visibleCourseOne);
        em.persist(visibleCourseTwo);
        em.persist(noVisibleCourseZero);
        em.persist(noVisibleCourseOne);

        Optional<CoursesQuantityByInstructorNameProjection> possibleCourseQuantityByInstructorName = courseRepository.getInstructorWithMoreCourses();

        assertThat(possibleCourseQuantityByInstructorName.get())
                .extracting("instructorName", "quantity").containsExactly(tuple("Paulo Silveira", 2).toArray());
    }
}
