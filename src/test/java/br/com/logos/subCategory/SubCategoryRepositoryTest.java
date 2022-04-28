package br.com.logos.subCategory;

import br.com.logos.category.Category;
import br.com.logos.category.CategoryRepository;
import br.com.logos.category.enums.CategoryStatus;
import br.com.logos.course.Course;
import br.com.logos.course.CourseRepository;
import br.com.logos.subCategory.enums.SubCategoryStatus;
import br.com.logos.utils.builders.CategoryBuilder;
import br.com.logos.utils.builders.CourseBuilder;
import br.com.logos.utils.builders.SubCategoryBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SubCategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private CourseRepository courseRepository;

    @ParameterizedTest
    @EnumSource(SubCategoryStatus.class)
    public void findAllByStatusShouldReturnACategoryAccordinToStatusByParameter(SubCategoryStatus status){
        Category category =  new CategoryBuilder()
                .withName("Programação")
                .withCode("programacao")
                .withStatus(CategoryStatus.ACTIVE)
                .withOrder(1)
                .withColorCode("#00c86f")
                .create();

        categoryRepository.save(category);

        SubCategory subCategory = new SubCategoryBuilder()
                .withCode("java")
                .withName("Java")
                .withStatus(status)
                .withOrder(1)
                .withCategory(category)
                .create();

        category.getSubCategories().add(subCategory);
        subCategoryRepository.save(subCategory);

        List<SubCategory> subCategories = subCategoryRepository.findAllByStatus(status);
        Assertions.assertThat(subCategories).extracting(SubCategory::getStatus).containsExactly(status);
    }

    @ParameterizedTest
    @ValueSource(strings = {"java", "java-oo"})
    public void findByCodeShouldReturnAOptionalFromSubCategoryWithTheRespectiveCode(String code){
        Category category =  new CategoryBuilder()
                .withName("Programação")
                .withCode("programacao")
                .withStatus(CategoryStatus.ACTIVE)
                .withOrder(1)
                .withColorCode("#00c86f")
                .create();

        categoryRepository.save(category);

        SubCategory subCategoryAdd = new SubCategoryBuilder()
                .withCode(code)
                .withName("subcategoria de programação")
                .withStatus(SubCategoryStatus.ACTIVE)
                .withOrder(1)
                .withCategory(category)
                .create();
        category.getSubCategories().add(subCategoryAdd);
        subCategoryRepository.save(subCategoryAdd);
        Optional<SubCategory> subCategory = subCategoryRepository.findByCode(code);
        assertThat(subCategory.isPresent()).isTrue();
        assertThat(subCategory.get()).extracting(SubCategory::getCode).isEqualTo(code);
    }

    @Test
    public void getAllByCategoryOrderByOrderShouldReturnASubCategoriesListFromCategoryCode(){
        List<Category> categories = Arrays.asList(
                new CategoryBuilder()
                        .withName("Programação")
                        .withCode("programacao")
                        .withStatus(CategoryStatus.ACTIVE)
                        .withOrder(1)
                        .withColorCode("#00c86f")
                        .create(),
                new CategoryBuilder()
                        .withName("Devops")
                        .withCode("devops")
                        .withStatus(CategoryStatus.ACTIVE)
                        .withOrder(1)
                        .withColorCode("#00c86f")
                        .create());

        categoryRepository.saveAll(categories);
        List<SubCategory> subCategories =  Arrays.asList(
                new SubCategoryBuilder()
                        .withCode("subcategory-one")
                        .withName("subcategoria de programação")
                        .withStatus(SubCategoryStatus.ACTIVE)
                        .withOrder(1)
                        .withCategory(categories.get(0))
                        .create(),
                new SubCategoryBuilder()
                        .withCode("subcategory-zero")
                        .withName("subcategoria de programação")
                        .withStatus(SubCategoryStatus.ACTIVE)
                        .withOrder(0)
                        .withCategory(categories.get(0))
                        .create(),
                new SubCategoryBuilder()
                        .withCode("subcategory-two")
                        .withName("subcategoria de devops")
                        .withStatus(SubCategoryStatus.ACTIVE)
                        .withOrder(1)
                        .withCategory(categories.get(1))
                        .create()
        );
        subCategoryRepository.saveAll(subCategories);
       List<SubCategoryProjection> subCategoryProjections = subCategoryRepository.getAllByCategoryOrderByOrder("programacao");
        assertThat(subCategoryProjections).extracting(SubCategoryProjection::getCode).containsExactly("subcategory-zero","subcategory-one");
    }

    @Test
    public void findAllByOrderByNameShouldReturnASubCategoryListOrderByName(){
        Category category = new CategoryBuilder()
                .withName("Programação")
                .withCode("programacao")
                .withStatus(CategoryStatus.ACTIVE)
                .withOrder(1)
                .withColorCode("#00c86f")
                .create();
        categoryRepository.save(category);

        List<SubCategory> subCategories =  Arrays.asList(
                new SubCategoryBuilder()
                        .withCode("subcategory-zero")
                        .withName("a-sub")
                        .withStatus(SubCategoryStatus.ACTIVE)
                        .withOrder(1)
                        .withCategory(category)
                        .create(),
                new SubCategoryBuilder()
                        .withCode("subcategory-two")
                        .withName("c-sub")
                        .withStatus(SubCategoryStatus.ACTIVE)
                        .withOrder(0)
                        .withCategory(category)
                        .create(),
                new SubCategoryBuilder()
                        .withCode("subcategory-one")
                        .withName("b-sub")
                        .withStatus(SubCategoryStatus.ACTIVE)
                        .withOrder(1)
                        .withCategory(category)
                        .create()
        );
        subCategoryRepository.saveAll(subCategories);
        List<SubCategory> subCategoriesOrderedByName = subCategoryRepository.findAllByOrderByName();
        assertThat(subCategoriesOrderedByName).extracting(SubCategory::getName).containsExactly("a-sub",
                "b-sub", "c-sub");
    }

    @Test
    public void getActiveSubCategoriesWithCoursesShouldReturnAActiveSubCategoriesWithCoursesProjectionIfSubCategoryIsActiveByCategoryCode(){
        List<Category> categories = Arrays.asList(
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
                        .withCategory(categories.get(0))
                        .create(),
                new SubCategoryBuilder()
                        .withCode("java-e-persistencia")
                        .withName("Java e Persistência")
                        .withStatus(SubCategoryStatus.ACTIVE)
                        .withOrder(2)
                        .withCategory(categories.get(0))
                        .create(),
                new SubCategoryBuilder()
                        .withCode("builds-e-controle-de-versao")
                        .withName("Builds e Controle de versão")
                        .withStatus(SubCategoryStatus.DISABLED)
                        .withCategory(categories.get(1))
                        .create());

        categories.get(0).getSubCategories().add(subCategories.get(0));
        categories.get(0).getSubCategories().add(subCategories.get(1));
        categories.get(1).getSubCategories().add(subCategories.get(2));

        subCategoryRepository.saveAll(subCategories);

        List<Course> courses = Arrays.asList(
                new CourseBuilder().withCode("java-oo")
                        .withName("Java OO: Introdução à Orientação a Objetos")
                        .withEstimatedTime(8)
                        .withInstructorName("Paulo Silveira")
                        .withSubCategory(subCategories.get(0))
                        .create(),
                new CourseBuilder().withCode("jpa")
                        .withName("jpa")
                        .withEstimatedTime(9)
                        .withInstructorName("Thais")
                        .withSubCategory(subCategories.get(1))
                        .create());

        subCategories.get(0).getCourses().add(courses.get(0));
        subCategories.get(2).getCourses().add(courses.get(1));

        courseRepository.saveAll(courses);

        List<ActiveSubCategoriesWithCoursesProjection> subCategoriesWithCoursesProjections = subCategoryRepository.getActiveSubCategoriesWithCourses("programacao");

        assertThat(subCategoriesWithCoursesProjections)
                .hasSize(2)
                .extracting(ActiveSubCategoriesWithCoursesProjection::getCode)
                .containsExactly("java","java-e-persistencia");
    }
}
