package br.com.logos.category.projection;

import br.com.logos.category.ActiveCategoryWithActiveSubCategoriesProjection;
import br.com.logos.category.Category;
import br.com.logos.category.enums.CategoryStatus;
import br.com.logos.course.Course;
import br.com.logos.subCategory.SubCategory;
import br.com.logos.subCategory.enums.SubCategoryStatus;
import br.com.logos.utils.builders.CourseBuilder;
import br.com.logos.utils.builders.SubCategoryBuilder;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class ActiveCategoryWithActiveSubCategoriesProjectionTest {

    class ActiveCategoryWithActiveSubCategoriesProjectionImpl implements ActiveCategoryWithActiveSubCategoriesProjection {

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
                    .withInstructorName("Madu")
                    .withEstimatedTime(2)
                    .withVisibility(true)
                    .withSubCategory(disabledSubCategory)
                    .create();

            activeSubCategoryZero.getCourses().addAll(List.of(visibleCourseFromActiveSubCategory, noVisibleCourseFromActiveSubCategory));
            disabledSubCategory.getCourses().addAll(List.of(visibleCourseFromDisabledSubCategory, noVisibleCourseFromDisabledSubCategory));

            return List.of(activeSubCategoryZero, disabledSubCategory, disabledSubCategory);
        }
    }
    @Test
    public void getActiveSubCategoriesWithPublicCourseShouldReturnOnlyActiveCategoriesWithActiveSubCategoriesAndVisibleCourses(){
        ActiveCategoryWithActiveSubCategoriesProjectionImpl projection = new ActiveCategoryWithActiveSubCategoriesProjectionImpl();
        assertThat(projection.getActiveSubCategoriesWithVisibleCoursesSortedBySubCategoryOrder())
                .extracting(SubCategory::getCode)
                .hasSize(1)
                .containsExactly("java")
                .doesNotContain("php");
        assertThat(projection.getActiveSubCategoriesWithVisibleCoursesSortedBySubCategoryOrder())
                .extracting(SubCategory::getCourses).extracting(courses -> courses.get(0))
                .extracting("name", "code")
                .containsExactly(tuple("OO Java", "java-oo"))
                .doesNotContain(tuple("Boas práticas com java", "boas-praticas-java"),
                        tuple("PHP orientado a objetos", "php-oo"),
                        tuple("Php Iniciante", "php-iniciante")
                );
    }
}
