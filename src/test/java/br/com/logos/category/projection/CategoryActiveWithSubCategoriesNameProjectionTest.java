package br.com.logos.category.projection;

import br.com.logos.category.Category;
import br.com.logos.category.CategoryActiveWithSubCategoriesNameProjection;
import br.com.logos.category.enums.CategoryStatus;
import br.com.logos.course.Course;
import br.com.logos.subCategory.SubCategory;
import br.com.logos.subCategory.enums.SubCategoryStatus;
import br.com.logos.utils.builders.CourseBuilder;
import br.com.logos.utils.builders.SubCategoryBuilder;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryActiveWithSubCategoriesNameProjectionTest {

    class CategoryActiveWithSubCategoriesNameProjectionImpl implements CategoryActiveWithSubCategoriesNameProjection {

        @Override
        public String getName() {
            return "test";
        }

        @Override
        public String getCode() {
            return "test-code";
        }

        @Override
        public String getImageUrl() {
            return "test.com";
        }

        @Override
        public List<SubCategory> getSubCategories() {
            Category activeCategoryZero = new Category("Programação", "programacao", CategoryStatus.ACTIVE);
            //TODO criar apenas 2 subs uma ativa e outra inativa

            SubCategory activeSubCategoryZero = new SubCategoryBuilder()
                    .withName("Java")
                    .withCode("java")
                    .withOrder(0)
                    .withCategory(activeCategoryZero)
                    .withStatus(SubCategoryStatus.ACTIVE)
                    .create();

            SubCategory activeSubCategoryOne = new SubCategoryBuilder()
                    .withName("Java Persistence")
                    .withCode("java-persistence")
                    .withOrder(1)
                    .withCategory(activeCategoryZero)
                    .withStatus(SubCategoryStatus.ACTIVE)
                    .create();

            activeCategoryZero.getSubCategories().addAll(List.of(activeSubCategoryZero, activeSubCategoryOne));

            SubCategory disabledSubCategory = new SubCategoryBuilder()
                    .withName("php")
                    .withCode("php")
                    .withStatus(SubCategoryStatus.DISABLED)
                    .withOrder(3)
                    .withCategory(activeCategoryZero)
                    .create();
            //TODO colocar em cima
            activeCategoryZero.getSubCategories().add(disabledSubCategory);

            //TODO todas as combinações possiveis da visibilidade do curso com o status da categoria
            List<Course> courses = Arrays.asList(
                    new CourseBuilder()
                            .withCode("java-oo")
                            .withName("OO Java")
                            .withInstructorName("Paulo")
                            .withEstimatedTime(2)
                            .withVisibility(true)
                            .withSubCategory(activeSubCategoryZero)
                            .create(),
                    new CourseBuilder()
                            .withCode("jpa")
                            .withName("JPA")
                            .withInstructorName("Paulo")
                            .withEstimatedTime(2)
                            .withVisibility(true)
                            .withSubCategory(activeSubCategoryOne)
                            .create(),
                    new CourseBuilder()
                            .withCode("boas-praticas-java")
                            .withName("Boas práticas com java")
                            .withInstructorName("Paulo")
                            .withEstimatedTime(2)
                            .withVisibility(false)
                            .withSubCategory(activeSubCategoryZero)
                            .create(),
                    new CourseBuilder()
                            .withCode("php-oo")
                            .withName("Php OO")
                            .withInstructorName("Paulo")
                            .withEstimatedTime(2)
                            .withVisibility(true)
                            .withSubCategory(disabledSubCategory)
                            .create()
            );

            activeSubCategoryZero.getCourses().addAll(List.of(courses.get(0), courses.get(2)));
            activeSubCategoryOne.getCourses().add(courses.get(1));
            disabledSubCategory.getCourses().add(courses.get(3));

            return List.of(activeSubCategoryZero, activeSubCategoryOne, disabledSubCategory);
        }
    }
    @Test
    public void getActiveSubCategoriesWithPublicCourseShouldReturnOnlyActiveCategoriesWithActiveSubCategoriesAndVisibleCourses(){
        CategoryActiveWithSubCategoriesNameProjectionImpl projection = new  CategoryActiveWithSubCategoriesNameProjectionImpl();
        //TODO colocar um doesNotContains
        assertThat(projection.getActiveSubCategoriesWithPublicCourse()).extracting(SubCategory::getCode).containsExactly("java","java-persistence");
    }
}
