package br.com.logos.category;

import br.com.logos.category.enums.CategoryStatus;
import br.com.logos.course.Course;
import br.com.logos.subCategory.SubCategory;
import br.com.logos.subCategory.enums.SubCategoryStatus;
import br.com.logos.utils.builders.CategoryBuilder;
import br.com.logos.utils.builders.CourseBuilder;
import br.com.logos.utils.builders.SubCategoryBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EntityManager em;

    @ParameterizedTest
    @ValueSource(strings = {"devops"})
    public void getCategoryByCodeShouldReturnTheCategoryProjectionWithRespectiveCode(String code) {
        Optional<CategoryProjection> possibleCategory = categoryRepository.getCategoryByCode(code);
        assertThat(possibleCategory).isEmpty();
        em.persist(new CategoryBuilder()
                .withCode("devops")
                .withName("DevOps")
                .withColorCode("#f16165")
                .withImageUrl("https://www.alura.com.br/assets/api/formacoes/categorias/512/devops-transparent.png").create());
        possibleCategory = categoryRepository.getCategoryByCode(code);

        assertThat(possibleCategory).isNotEmpty();
        assertThat(possibleCategory.get().getCode()).isEqualTo("devops");
    }

    @Test
    public void getActiveCategoriesWithActiveSubCategoriesShouldReturnActiveCategoryWithActiveSubCategoriesProjectionIfExistsAnyActiveSubCategoryWithVisibleCourse() {
        Category disabledCategory = new CategoryBuilder()
                .withName("Business")
                .withCode("business")
                .withOrder(0)
                .withColorCode("#ff8c2a")
                .create();

        Category activeCategoryZero = new CategoryBuilder()
                .withName("Programação")
                .withCode("programacao")
                .withStatus(CategoryStatus.ACTIVE)
                .withOrder(1)
                .withColorCode("#00c86f")
                .create();

        Category activeCategoryOne = new CategoryBuilder()
                .withName("DevOps")
                .withCode("devops")
                .withStatus(CategoryStatus.ACTIVE)
                .withOrder(2)
                .withColorCode("#f16165")
                .create();

        SubCategory activeSubCategoryFromActiveCategoryZero = new SubCategoryBuilder()
                .withCode("java")
                .withName("Java")
                .withStatus(SubCategoryStatus.ACTIVE)
                .withOrder(1)
                .withCategory(activeCategoryZero)
                .create();

        activeCategoryZero.getSubCategories().add(activeSubCategoryFromActiveCategoryZero);

        SubCategory activeSubCategoryFromActiveCategoryOne = new SubCategoryBuilder()
                .withCode("builds-e-controle-de-versao")
                .withName("Builds e Controle de versão")
                .withStatus(SubCategoryStatus.ACTIVE)
                .withOrder(3)
                .withCategory(activeCategoryOne)
                .create();

        activeCategoryOne.getSubCategories().add(activeSubCategoryFromActiveCategoryOne);

        SubCategory activeSubCategoryFromDisabledCategory = new SubCategoryBuilder()
                .withCode("gestao")
                .withName("Gestão")
                .withStatus(SubCategoryStatus.ACTIVE)
                .withOrder(2)
                .withCategory(disabledCategory)
                .create();

        disabledCategory.getSubCategories().add(activeSubCategoryFromDisabledCategory);

        SubCategory disabledSubCategoryFromActiveCategory = new SubCategoryBuilder()
                .withCode("java-e-persistencia")
                .withName("Java e Persistência")
                .withOrder(2)
                .withCategory(activeCategoryZero)
                .create();

        activeCategoryZero.getSubCategories().add(disabledSubCategoryFromActiveCategory);

        Course visibleCourseFromActiveSubCategoryZero = new CourseBuilder().withCode("java-oo")
                .withName("Java OO: Introdução à Orientação a Objetos")
                .withEstimatedTime(8)
                .withInstructorName("Paulo Silveira")
                .withVisibility(true)
                .withSubCategory(activeSubCategoryFromActiveCategoryZero)
                .create();

        activeSubCategoryFromActiveCategoryZero.getCourses().add(visibleCourseFromActiveSubCategoryZero);

        Course noVisibleCourseFromActiveSubCategoryOne = new CourseBuilder().withCode("git-e-github")
                .withName("Git e Github")
                .withEstimatedTime(9)
                .withInstructorName("Thais")
                .withVisibility(false)
                .withSubCategory(activeSubCategoryFromActiveCategoryOne)
                .create();

        activeSubCategoryFromActiveCategoryOne.getCourses().add(noVisibleCourseFromActiveSubCategoryOne);

        Course visibleCourseFromDisabledSubCategoryWithActiveCategory = new CourseBuilder().withCode("jpa")
                .withName("Jpa")
                .withEstimatedTime(9)
                .withInstructorName("Alessandro")
                .withVisibility(true)
                .withSubCategory(disabledSubCategoryFromActiveCategory)
                .create();

        disabledSubCategoryFromActiveCategory.getCourses().add(visibleCourseFromDisabledSubCategoryWithActiveCategory);

        Course visibleCourseFromActiveSubCategoryWithDisabledCategory = new CourseBuilder().withCode("negocios")
                .withName("Negócios")
                .withEstimatedTime(9)
                .withInstructorName("Camila")
                .withVisibility(true)
                .withSubCategory(activeSubCategoryFromDisabledCategory)
                .create();

        activeSubCategoryFromDisabledCategory.getCourses().add(visibleCourseFromActiveSubCategoryWithDisabledCategory);

        em.persist(disabledCategory);
        em.persist(activeCategoryZero);
        em.persist(activeCategoryOne);

        em.persist(activeSubCategoryFromActiveCategoryZero);
        em.persist(activeSubCategoryFromActiveCategoryOne);
        em.persist(activeSubCategoryFromDisabledCategory);
        em.persist(disabledSubCategoryFromActiveCategory);

        em.persist(visibleCourseFromActiveSubCategoryZero);
        em.persist(noVisibleCourseFromActiveSubCategoryOne);
        em.persist(visibleCourseFromDisabledSubCategoryWithActiveCategory);
        em.persist(visibleCourseFromActiveSubCategoryWithDisabledCategory);

        List<ActiveCategoryWithActiveSubCategoriesProjection> categoriesFromDatabase = categoryRepository.getActiveCategoriesWithActiveSubCategories();

        assertThat(categoriesFromDatabase)
                .hasSize(1)
                .extracting(ActiveCategoryWithActiveSubCategoriesProjection::getCode)
                .containsExactly("programacao").doesNotContain("devops", "business");

        assertThat(categoriesFromDatabase)
                .hasSize(1)
                .extracting(cat -> cat.getActiveSubCategoriesWithVisibleCoursesSortedBySubCategoryOrder())
                .containsOnly(List.of(activeSubCategoryFromActiveCategoryZero))
                .doesNotContain(List.of(activeSubCategoryFromActiveCategoryOne, activeSubCategoryFromDisabledCategory,
                        disabledSubCategoryFromActiveCategory));

        assertThat(categoriesFromDatabase)
                .hasSize(1)
                .extracting(cat -> cat.getActiveSubCategoriesWithVisibleCoursesSortedBySubCategoryOrder().get(0).getCourses())
                .hasSize(1).doesNotContain(List.of(noVisibleCourseFromActiveSubCategoryOne,
                        visibleCourseFromDisabledSubCategoryWithActiveCategory, visibleCourseFromActiveSubCategoryWithDisabledCategory));
    }
}

