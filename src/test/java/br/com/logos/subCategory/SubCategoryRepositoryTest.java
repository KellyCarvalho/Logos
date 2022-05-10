package br.com.logos.subCategory;

import br.com.logos.category.Category;
import br.com.logos.category.enums.CategoryStatus;
import br.com.logos.course.Course;
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

import static org.assertj.core.api.Assertions.assertThat;


@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SubCategoryRepositoryTest {

    @Autowired
    private SubCategoryRepository subCategoryRepository;


    @Autowired
    private EntityManager em;

    @Test
    public void getAllByCategoryOrderByOrderShouldReturnSubCategoriesByCategoryCode() {
        Category category = new CategoryBuilder()
                .withName("Programação")
                .withCode("programacao")
                .withStatus(CategoryStatus.ACTIVE)
                .withOrder(1)
                .withColorCode("#00c86f")
                .create();

        SubCategory activeSubCategoryZero = new SubCategoryBuilder()
                .withCode("subcategory-zero")
                .withName("subcategoria de programação")
                .withStatus(SubCategoryStatus.ACTIVE)
                .withOrder(0)
                .withCategory(category)
                .create();

        SubCategory activeSubCategoryOne = new SubCategoryBuilder()
                .withCode("subcategory-one")
                .withName("subcategoria de programação")
                .withStatus(SubCategoryStatus.ACTIVE)
                .withOrder(1)
                .withCategory(category)
                .create();

        SubCategory disabledSubCategory = new SubCategoryBuilder()
                .withCode("subcategory-two")
                .withName("subcategoria de devops")
                .withOrder(2)
                .withCategory(category)
                .create();

        em.persist(category);
        em.persist(activeSubCategoryZero);
        em.persist(activeSubCategoryOne);
        em.persist(disabledSubCategory);

        List<SubCategoryProjection> subCategoryProjections = subCategoryRepository.getAllByCategoryOrderByOrder("programacao");

        assertThat(subCategoryProjections).extracting(SubCategoryProjection::getCode).containsExactly("subcategory-zero", "subcategory-one", "subcategory-two");
    }

    @Test
    public void getActiveSubCategoriesWithCoursesShouldReturnActiveSubCategoriesWithCoursesProjectionIfSubCategoryIsActiveByCategoryCode() {
        Category categoryZero = new CategoryBuilder()
                .withName("Programação")
                .withCode("programacao")
                .withStatus(CategoryStatus.ACTIVE)
                .withOrder(1)
                .withColorCode("#00c86f")
                .create();

        SubCategory subCategoryZero = new SubCategoryBuilder()
                .withCode("java")
                .withName("Java")
                .withStatus(SubCategoryStatus.ACTIVE)
                .withOrder(1)
                .withCategory(categoryZero)
                .create();

        categoryZero.getSubCategories().add(subCategoryZero);

        SubCategory subCategoryOne = new SubCategoryBuilder()
                .withCode("java-e-persistencia")
                .withName("Java e persistência")
                .withStatus(SubCategoryStatus.ACTIVE)
                .withOrder(2)
                .withCategory(categoryZero)
                .create();

        categoryZero.getSubCategories().add(subCategoryOne);

        SubCategory subCategoryTwo = new SubCategoryBuilder()
                .withCode("php")
                .withName("php")
                .withCategory(categoryZero)
                .create();

        categoryZero.getSubCategories().add(subCategoryTwo);

        Course visibleCourseZero = new CourseBuilder()
                .withCode("java-oo")
                .withName("Java OO: Introdução à Orientação a Objetos")
                .withEstimatedTime(8)
                .withInstructorName("Paulo Silveira")
                .withSubCategory(subCategoryZero)
                .withVisibility(true)
                .create();
        subCategoryZero.getCourses().add(visibleCourseZero);

        Course noVisibleCourseOne = new CourseBuilder()
                .withCode("jpa")
                .withName("jpa")
                .withEstimatedTime(9)
                .withInstructorName("Thais")
                .withSubCategory(subCategoryOne)
                .create();

        subCategoryOne.getCourses().add(noVisibleCourseOne);

        Course visibleCourseTwo = new CourseBuilder()
                .withCode("php-iniciante")
                .withName("PHP Iniciante")
                .withEstimatedTime(9)
                .withInstructorName("Thais")
                .withSubCategory(subCategoryTwo)
                .withVisibility(true)
                .create();

        subCategoryTwo.getCourses().add(visibleCourseTwo);

        em.persist(categoryZero);
        em.persist(subCategoryZero);
        em.persist(subCategoryOne);
        em.persist(subCategoryTwo);

        em.persist(visibleCourseZero);
        em.persist(noVisibleCourseOne);
        em.persist(visibleCourseTwo);

        List<ActiveSubCategoriesWithCoursesProjection> subCategoriesWithCoursesProjection = subCategoryRepository.getActiveSubCategoriesWithCourses("programacao");

        assertThat(subCategoriesWithCoursesProjection)
                .hasSize(2)
                .extracting(ActiveSubCategoriesWithCoursesProjection::getCode)
                .containsExactly("java", "java-e-persistencia")
                .doesNotContain("php");

        assertThat(subCategoriesWithCoursesProjection)
                .extracting(cat -> cat.getVisibleCoursesWithActiveSubCategorySortedBySubCategoryOrder())
                .contains(List.of(visibleCourseZero))
                .doesNotContain(List.of(noVisibleCourseOne, visibleCourseTwo));
    }
}
