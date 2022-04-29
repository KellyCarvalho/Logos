package br.com.logos.course;

import br.com.logos.category.Category;
import br.com.logos.category.CategoryRepository;
import br.com.logos.category.enums.CategoryStatus;
import br.com.logos.subCategory.SubCategory;
import br.com.logos.subCategory.SubCategoryRepository;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CourseRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void findByVisibilityAndSubCategory_Category_StatusOrderBySubCategory_Category_OrderShouldReturnCoursesByVisibilityAndCategoryStatusOrdered(){
        List<Category> categories = Arrays.asList(
                new CategoryBuilder()
                        .withName("Programação")
                        .withCode("programacao")
                        .withStatus(CategoryStatus.ACTIVE)
                        .withOrder(0)
                        .withColorCode("#00c86f")
                        .create(),
                new CategoryBuilder()
                        .withName("DevOps")
                        .withCode("devops")
                        .withStatus(CategoryStatus.DISABLED)
                        .withOrder(2)
                        .withColorCode("#f16165")
                        .create());

        categoryRepository.saveAll(categories);

        List<SubCategory> subCategories = Arrays.asList(
                new SubCategoryBuilder()
                        .withCode("java")
                        .withName("Java")
                        .withStatus(SubCategoryStatus.ACTIVE)
                        .withOrder(0)
                        .withCategory(categories.get(0))
                        .create(),
                new SubCategoryBuilder()
                        .withCode("java-persistence")
                        .withName("Java persistence")
                        .withStatus(SubCategoryStatus.ACTIVE)
                        .withOrder(1)
                        .withCategory(categories.get(0))
                        .create(),
                new SubCategoryBuilder()
                        .withCode("builds-e-controle-de-versao")
                        .withName("Builds e Controle de versão")
                        .withStatus(SubCategoryStatus.ACTIVE)
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
                        .withVisibility(true)
                        .withSubCategory(subCategories.get(0))
                        .create(),
                new CourseBuilder().withCode("jpa")
                        .withName("Jpa")
                        .withEstimatedTime(8)
                        .withInstructorName("Paulo Silveira")
                        .withVisibility(true)
                        .withSubCategory(subCategories.get(1))
                        .create(),
                new CourseBuilder().withCode("git-e-github")
                        .withName("Git e Github")
                        .withEstimatedTime(9)
                        .withInstructorName("Thais")
                        .withVisibility(true)
                        .withSubCategory(subCategories.get(2))
                        .create());

        subCategories.get(0).getCourses().add(courses.get(0));
        subCategories.get(1).getCourses().add(courses.get(1));
        subCategories.get(1).getCourses().add(courses.get(2));

        courseRepository.saveAll(courses);

        List<Course> courseFromDatabase = courseRepository.findByVisibilityAndSubCategory_Category_StatusOrderBySubCategory_Category_Order(true, CategoryStatus.ACTIVE);

        assertThat(courseFromDatabase).hasSize(2).extracting(Course::getCode).containsExactly("java-oo","jpa");
    }

    @Test
    public void getAllCoursesCountByCategoryShouldReturnCategoryNameAndQuantityCoursesFromIt(){
        List<Category> categories = Arrays.asList(
                new CategoryBuilder()
                        .withName("Programação")
                        .withCode("programacao")
                        .withStatus(CategoryStatus.ACTIVE)
                        .withOrder(0)
                        .withColorCode("#00c86f")
                        .create(),
                new CategoryBuilder()
                        .withName("DevOps")
                        .withCode("devops")
                        .withStatus(CategoryStatus.DISABLED)
                        .withOrder(2)
                        .withColorCode("#f16165")
                        .create());

        categoryRepository.saveAll(categories);

        List<SubCategory> subCategories = Arrays.asList(
                new SubCategoryBuilder()
                        .withCode("java")
                        .withName("Java")
                        .withStatus(SubCategoryStatus.ACTIVE)
                        .withOrder(0)
                        .withCategory(categories.get(0))
                        .create(),
                new SubCategoryBuilder()
                        .withCode("java-persistence")
                        .withName("Java persistence")
                        .withStatus(SubCategoryStatus.ACTIVE)
                        .withOrder(1)
                        .withCategory(categories.get(0))
                        .create(),
                new SubCategoryBuilder()
                        .withCode("builds-e-controle-de-versao")
                        .withName("Builds e Controle de versão")
                        .withStatus(SubCategoryStatus.ACTIVE)
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
                        .withVisibility(true)
                        .withSubCategory(subCategories.get(0))
                        .create(),
                new CourseBuilder().withCode("jpa")
                        .withName("Jpa")
                        .withEstimatedTime(8)
                        .withInstructorName("Paulo Silveira")
                        .withVisibility(true)
                        .withSubCategory(subCategories.get(1))
                        .create(),
                new CourseBuilder().withCode("git-e-github")
                        .withName("Git e Github")
                        .withEstimatedTime(9)
                        .withInstructorName("Thais")
                        .withVisibility(true)
                        .withSubCategory(subCategories.get(2))
                        .create());

        subCategories.get(0).getCourses().add(courses.get(0));
        subCategories.get(1).getCourses().add(courses.get(1));
        subCategories.get(1).getCourses().add(courses.get(2));

        courseRepository.saveAll(courses);

       List <CourseByCategoryProjection> courseByCategoryProjections = courseRepository.getAllCoursesCountByCategory();

       assertThat(courseByCategoryProjections).hasSize(2).extracting(CourseByCategoryProjection::getQuantity).containsExactly(2,1);
       assertThat(courseByCategoryProjections).hasSize(2).extracting(CourseByCategoryProjection::getCategoryName).containsExactly("Programação", "DevOps");
    }

    @Test
    public void reportInstructorWithMoreCoursesShouldReturnTheInstructorNameWithMoreCourses(){
        List<Category> categories = Arrays.asList(
                new CategoryBuilder()
                        .withName("Programação")
                        .withCode("programacao")
                        .withStatus(CategoryStatus.ACTIVE)
                        .withOrder(0)
                        .withColorCode("#00c86f")
                        .create(),
                new CategoryBuilder()
                        .withName("DevOps")
                        .withCode("devops")
                        .withStatus(CategoryStatus.DISABLED)
                        .withOrder(2)
                        .withColorCode("#f16165")
                        .create());

        categoryRepository.saveAll(categories);

        List<SubCategory> subCategories = Arrays.asList(
                new SubCategoryBuilder()
                        .withCode("java")
                        .withName("Java")
                        .withStatus(SubCategoryStatus.ACTIVE)
                        .withOrder(0)
                        .withCategory(categories.get(0))
                        .create(),
                new SubCategoryBuilder()
                        .withCode("java-persistence")
                        .withName("Java persistence")
                        .withStatus(SubCategoryStatus.ACTIVE)
                        .withOrder(1)
                        .withCategory(categories.get(0))
                        .create(),
                new SubCategoryBuilder()
                        .withCode("builds-e-controle-de-versao")
                        .withName("Builds e Controle de versão")
                        .withStatus(SubCategoryStatus.ACTIVE)
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
                        .withVisibility(true)
                        .withSubCategory(subCategories.get(0))
                        .create(),
                new CourseBuilder().withCode("jpa")
                        .withName("Jpa")
                        .withEstimatedTime(8)
                        .withInstructorName("Paulo Silveira")
                        .withVisibility(true)
                        .withSubCategory(subCategories.get(1))
                        .create(),
                new CourseBuilder().withCode("git-e-github")
                        .withName("Git e Github")
                        .withEstimatedTime(9)
                        .withInstructorName("Pedro")
                        .withVisibility(true)
                        .withSubCategory(subCategories.get(2))
                        .create());

        subCategories.get(0).getCourses().add(courses.get(0));
        subCategories.get(1).getCourses().add(courses.get(1));
        subCategories.get(1).getCourses().add(courses.get(2));

        courseRepository.saveAll(courses);

        Optional<CoursesQuantityByInstructorNameProjection> possibleCourseQuantityByInstructorName = courseRepository.reportInstructorWithMoreCourses();

        assertThat(possibleCourseQuantityByInstructorName.get()).extracting(CoursesQuantityByInstructorNameProjection::getInstructorName).isEqualTo("Paulo Silveira");
        assertThat(possibleCourseQuantityByInstructorName.get()).extracting(CoursesQuantityByInstructorNameProjection::getQuantity).isEqualTo(2);
    }

    @Test
    public void findAllBySubCategoryShouldReturnAPageableFromSubCategory(){
        Category category =
                new CategoryBuilder()
                        .withName("Programação")
                        .withCode("programacao")
                        .withStatus(CategoryStatus.ACTIVE)
                        .withOrder(0)
                        .withColorCode("#00c86f")
                        .create();

        categoryRepository.save(category);

        SubCategory subCategory =
                new SubCategoryBuilder()
                        .withCode("java")
                        .withName("Java")
                        .withStatus(SubCategoryStatus.ACTIVE)
                        .withOrder(0)
                        .withCategory(category)
                        .create();

        category.getSubCategories().add(subCategory);

        subCategoryRepository.save(subCategory);

        List<Course> courses = Arrays.asList(
                new CourseBuilder().withCode("java-oo")
                        .withName("Java OO: Introdução à Orientação a Objetos")
                        .withEstimatedTime(8)
                        .withInstructorName("Paulo Silveira")
                        .withVisibility(true)
                        .withSubCategory(subCategory)
                        .create(),
                new CourseBuilder().withCode("jpa")
                        .withName("Jpa")
                        .withEstimatedTime(8)
                        .withInstructorName("Paulo Silveira")
                        .withVisibility(true)
                        .withSubCategory(subCategory)
                        .create());

        subCategory.getCourses().addAll(courses);

        courseRepository.saveAll(courses);

        Pageable pageable = PageRequest.of(0, 5);

        Page<Course> coursePage = courseRepository.findAllBySubCategory(subCategory, pageable);

        assertThat(coursePage).extracting(Course::getCode).hasSize(2).containsExactly("java-oo","jpa");
    }

    @ParameterizedTest
    @ValueSource(strings = {"java-oo", "jpa"})
    public void findByCodeShouldReturnACourseByCode(String code){
        Category category =
                new CategoryBuilder()
                        .withName("Programação")
                        .withCode("programacao")
                        .withStatus(CategoryStatus.ACTIVE)
                        .withOrder(0)
                        .withColorCode("#00c86f")
                        .create();

        categoryRepository.save(category);

        SubCategory subCategory =
                new SubCategoryBuilder()
                        .withCode("java")
                        .withName("Java")
                        .withStatus(SubCategoryStatus.ACTIVE)
                        .withOrder(0)
                        .withCategory(category)
                        .create();

        category.getSubCategories().add(subCategory);

        subCategoryRepository.save(subCategory);

        List<Course> courses = Arrays.asList(
                new CourseBuilder().withCode("java-oo")
                        .withName("Java OO: Introdução à Orientação a Objetos")
                        .withEstimatedTime(8)
                        .withInstructorName("Paulo Silveira")
                        .withVisibility(true)
                        .withSubCategory(subCategory)
                        .create(),
                new CourseBuilder().withCode("jpa")
                        .withName("Jpa")
                        .withEstimatedTime(8)
                        .withInstructorName("Paulo Silveira")
                        .withVisibility(true)
                        .withSubCategory(subCategory)
                        .create());

        subCategory.getCourses().addAll(courses);

        courseRepository.saveAll(courses);

        Optional<Course> possibleCourse = courseRepository.findByCode(code);

        assertThat(possibleCourse.get()).extracting(Course::getCode).isEqualTo(code);
        assertThat(possibleCourse.isPresent()).isTrue();
    }
}
