package br.com.logos.subCategory.projection;

import br.com.logos.category.Category;
import br.com.logos.category.enums.CategoryStatus;
import br.com.logos.course.Course;
import br.com.logos.subCategory.ActiveSubCategoriesWithCoursesProjection;
import br.com.logos.subCategory.SubCategory;
import br.com.logos.subCategory.enums.SubCategoryStatus;
import br.com.logos.utils.builders.CourseBuilder;
import br.com.logos.utils.builders.SubCategoryBuilder;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ActiveSubCategoriesWithCoursesProjectionTest {

    class ActiveSubCategoriesWithCoursesProjectionImpl implements ActiveSubCategoriesWithCoursesProjection {


        @Override
        public String getName() {
            return "Test";
        }

        @Override
        public String getCode() {
            return "test-code";
        }

        @Override
        public List<Course> getCourses() {
            Category activeCategoryZero = new Category("Programação", "programacao", CategoryStatus.ACTIVE);

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

            activeCategoryZero.getSubCategories().add(disabledSubCategory);

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

            return courses;
        }
    }

    @Test
    public void getActiveSubCategoriesWithPublicCourseShouldReturnOnlyVisibleCourses(){
        ActiveSubCategoriesWithCoursesProjectionImpl projection = new ActiveSubCategoriesWithCoursesProjectionImpl();

        assertThat(projection.getActiveSubCategoriesWithPublicCourse()).extracting(Course::getCode).containsExactly("java-oo","jpa");
    }
}
