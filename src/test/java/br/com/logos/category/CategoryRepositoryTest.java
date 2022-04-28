package br.com.logos.category;

import br.com.logos.category.enums.CategoryStatus;
import br.com.logos.course.Course;
import br.com.logos.course.CourseRepository;
import br.com.logos.subCategory.SubCategory;
import br.com.logos.subCategory.SubCategoryRepository;
import br.com.logos.subCategory.enums.SubCategoryStatus;
import br.com.logos.utils.builders.CategoryBuilder;
import br.com.logos.utils.builders.CourseBuilder;
import br.com.logos.utils.builders.SubCategoryBuilder;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Comparator;
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
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TestEntityManager em;

    @ParameterizedTest
    @ValueSource(strings = {"devops"})
    public void getCategoryByCodeShouldReturnTheCategoryProjectionWithRespectiveCode(String code) {
        categoryRepository.save(new CategoryBuilder()
                .withCode("devops")
                .withName("DevOps")
                        .withColorCode("#f16165")
                .withImageUrl("https://www.alura.com.br/assets/api/formacoes/categorias/512/devops-transparent.png").create());
        Optional<CategoryProjection> possibleCategory = categoryRepository.getCategoryByCode(code);
        Assert.assertEquals(Arrays.asList(possibleCategory.get().getName(), possibleCategory.get().getCode(),
                        possibleCategory.get().getImageUrl()),
                Arrays.asList("DevOps", "devops", "https://www.alura.com.br/assets/api/formacoes/categorias/512/devops-transparent.png"));
        Assert.assertTrue(possibleCategory.isPresent());
    }

    @Test
    public void findByOrderByOrderShouldReturnACategoriesListInOrderByOrder() {
        List<Category> categories = Arrays.asList(
                new CategoryBuilder()
                        .withName("Business")
                        .withCode("business")
                        .withStatus(CategoryStatus.DISABLED)
                        .withOrder(0)
                        .withColorCode("#ff8c2a")
                        .create(),
                new CategoryBuilder()
                        .withName("Programação")
                        .withCode("programacao")
                        .withStatus(CategoryStatus.ACTIVE)
                        .withOrder(1)
                        .withColorCode("#00c86f").create(),
                new CategoryBuilder()
                        .withName("DevOps")
                        .withCode("devops")
                        .withStatus(CategoryStatus.ACTIVE)
                        .withOrder(2)
                        .withColorCode("#f16165")
                        .create()
        );

        categoryRepository.saveAll(categories);
        List<Category> categoriesFromDatabase = categoryRepository.findByOrderByOrder();

        Assert.assertEquals(categoriesFromDatabase, categories.stream().sorted(Comparator.comparing(Category::getOrder)).toList());
    }

    @Test
    public void findAllByOrderByNameShouldReturnACategoriesListInOrderByName() {
        List<Category> categories = Arrays.asList(
                        new CategoryBuilder()
                                .withName("Business")
                                .withCode("business")
                                .withStatus(CategoryStatus.DISABLED)
                                .withOrder(0)
                                .withColorCode("#ff8c2a")
                                .create(),
                        new CategoryBuilder()
                                .withName("Programação")
                                .withCode("programacao")
                                .withStatus(CategoryStatus.ACTIVE)
                                .withOrder(1)
                                .withColorCode("#00c86f")
                                .create(),
                        new CategoryBuilder()
                                .withName("DevOps")
                                .withCode("devops")
                                .withStatus(CategoryStatus.ACTIVE)
                                .withOrder(2)
                                .withColorCode("#f16165")
                                .create());
        categoryRepository.saveAll(categories);

        List<Category> categoriesFromDatabase = categoryRepository.findAllByOrderByName();

        Assert.assertEquals(categoriesFromDatabase, categories.stream().sorted(Comparator.comparing(Category::getName)).toList());
    }

    @Test
    public void getActiveCategoriesWithSubCategoriesShouldReturnCategoryActiveWithSubCategoriesNameProjectionIfACourseIsVisibleAndTheCategoryAndSubCategoryAreActive() {
        List<Category> categories = Arrays.asList(
                new CategoryBuilder()
                        .withName("Business")
                        .withCode("business")
                        .withStatus(CategoryStatus.DISABLED)
                        .withOrder(0)
                        .withColorCode("#ff8c2a")
                        .create(),
                new CategoryBuilder()
                        .withName("Programação")
                        .withCode("programacao")
                        .withStatus(CategoryStatus.ACTIVE)
                        .withOrder(1)
                        .withColorCode("#00c86f")
                        .create(),
                new CategoryBuilder()
                        .withName("DevOps")
                        .withCode("devops")
                        .withStatus(CategoryStatus.ACTIVE)
                        .withOrder(2)
                        .withColorCode("#f16165")
                        .create());

        categoryRepository.saveAll(categories);

              List<SubCategory> subCategories = Arrays.asList(
                      new SubCategoryBuilder()
                       .withCode("java")
                       .withName("Java")
                       .withStatus(SubCategoryStatus.ACTIVE)
                       .withOrder(1)
                       .withCategory(categories.get(1))
                       .create(),
                      new SubCategoryBuilder()
                       .withCode("java-e-persistencia")
                       .withName("Java e Persistência")
                       .withStatus(SubCategoryStatus.DISABLED)
                       .withOrder(2)
                       .withCategory(categories.get(1))
                       .create(),
                      new SubCategoryBuilder()
                      .withCode("builds-e-controle-de-versao")
                      .withName("Builds e Controle de versão")
                      .withStatus(SubCategoryStatus.ACTIVE)
                      .withCategory(categories.get(2))
                      .create());

        categories.get(0).getSubCategories().add(subCategories.get(0));
        categories.get(0).getSubCategories().add(subCategories.get(1));
        categories.get(2).getSubCategories().add(subCategories.get(2));

        subCategoryRepository.saveAll(subCategories);

       List<Course> courses = Arrays.asList(
                new CourseBuilder().withCode("java-oo")
                .withName("Java OO: Introdução à Orientação a Objetos")
                .withEstimatedTime(8)
                .withInstructorName("Paulo Silveira")
                .withVisibility(true)
                .withSubCategory(subCategories.get(0))
                .create(),
                new CourseBuilder().withCode("git-e-github")
                .withName("Git e Github")
                .withEstimatedTime(9)
                .withInstructorName("Thais")
                .withVisibility(true)
                .withSubCategory(subCategories.get(2))
                .create());

        subCategories.get(0).getCourses().add(courses.get(0));
        subCategories.get(2).getCourses().add(courses.get(1));

        courseRepository.saveAll(courses);

        List<CategoryActiveWithSubCategoriesNameProjection> categoriesFromDatabase = categoryRepository.getActiveCategoriesWithSubCategories();


        assertThat(categoriesFromDatabase)
                .hasSize(2)
                .extracting(CategoryActiveWithSubCategoriesNameProjection::getCode)
                .containsExactly("programacao","devops");
    }
}
