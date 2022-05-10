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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ActiveSubCategoriesWithVisibleCoursesProjectionTest {

    class ActiveSubCategoriesWithVisibleCoursesProjectionImpl implements ActiveSubCategoriesWithCoursesProjection {


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

            SubCategory disabledSubCategory = new SubCategoryBuilder()
                    .withName("Php")
                    .withCode("php")
                    .withOrder(1)
                    .withCategory(activeCategoryZero)
                    .withStatus(SubCategoryStatus.DISABLED)
                    .create();

            activeCategoryZero.getSubCategories().addAll(List.of(activeSubCategoryZero, disabledSubCategory));

            Course visibleCourseFromActiveSubCategory = new CourseBuilder()
                    .withCode("java-oo")
                    .withName("OO Java")
                    .withInstructorName("Paulo")
                    .withEstimatedTime(2)
                    .withVisibility(true)
                    .withSubCategory(activeSubCategoryZero)
                    .create();

            Course visibleCourseFromDisabledSubCategory = new CourseBuilder()
                    .withCode("php-oo")
                    .withName("PHP orientado a objetos")
                    .withInstructorName("Paulo")
                    .withEstimatedTime(2)
                    .withVisibility(true)
                    .withSubCategory(disabledSubCategory)
                    .create();

            Course noVisibleCourseFromActiveSubCategory = new CourseBuilder()
                    .withCode("boas-praticas-java")
                    .withName("Boas práticas com java")
                    .withInstructorName("Paulo")
                    .withEstimatedTime(2)
                    .withSubCategory(activeSubCategoryZero)
                    .create();

            Course noVisibleCourseFromDisabledSubCategory = new CourseBuilder()
                    .withCode("php-iniciante")
                    .withName("Php Iniciante")
                    .withInstructorName("Paulo")
                    .withEstimatedTime(2)
                    .withVisibility(true)
                    .withSubCategory(disabledSubCategory)
                    .create();

            activeSubCategoryZero.getCourses().addAll(List.of(visibleCourseFromActiveSubCategory, noVisibleCourseFromActiveSubCategory));
            disabledSubCategory.getCourses().addAll(List.of(visibleCourseFromDisabledSubCategory, noVisibleCourseFromDisabledSubCategory));

            return List.of(visibleCourseFromActiveSubCategory, visibleCourseFromDisabledSubCategory,
                    noVisibleCourseFromActiveSubCategory, noVisibleCourseFromDisabledSubCategory);
        }
    }

    @Test
    public void getVisibleCoursesWithActiveSubCategorySortedBySubCategoryOrderShouldReturnOnlyVisibleCoursesByActiveSubCategorySortedBySubCategoryOrder() {
        ActiveSubCategoriesWithVisibleCoursesProjectionImpl projection = new ActiveSubCategoriesWithVisibleCoursesProjectionImpl();
        assertThat(projection.getVisibleCoursesWithActiveSubCategorySortedBySubCategoryOrder())
                .extracting(Course::getCode)
                .hasSize(1)
                .containsExactly("java-oo")
                .doesNotContain("boas-praticas-java")
                .doesNotContain("php-oo")
                .doesNotContain("php-iniciante");
    }
}
