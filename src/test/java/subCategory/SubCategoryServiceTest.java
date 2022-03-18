package subCategory;

import Logos.category.Category;
import Logos.subCategory.SubCategory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;

import java.util.Arrays;
import java.util.List;

import static Logos.subCategory.SubCategoryService.getQuantitySubCategoriesActivesWithDescription;
import static Logos.subCategory.SubCategoryService.getSubCategoriesWithoutDescription;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static Logos.subCategory.enums.SubCategoryStatus.ACTIVE;
import static Logos.subCategory.enums.SubCategoryStatus.DISABLED;

public class SubCategoryServiceTest {

    private static Category category;
    private static List<SubCategory> subCategories;

    @BeforeAll
    public static void setUp() {
        category = new Category("programação", "programacao");
        subCategories = Arrays.asList(
                new SubCategory("Html", "html", "html", ACTIVE, 1, category),
                new SubCategory("javascript", "javascript", "javascript", DISABLED, 1, category),
                new SubCategory("jpa", "jpa", "", DISABLED, 1, category),
                new SubCategory("java", "java", null, ACTIVE, 1, category),
                new SubCategory("javaoo", "javaoo", "", ACTIVE, 1, category),
                new SubCategory("javaweb", "javaweb", "Java é uma ...", ACTIVE, 1, category));
    }

    @Test
    void getQuantitySubCategoriesActivesWithDescriptionShouldReturnTheQuantitySubCategoriesActivesWithDescription() {
        assertEquals(getQuantitySubCategoriesActivesWithDescription(subCategories), 3L);
    }

    @ParameterizedTest
    @EmptySource
    void getSubCategoriesWithoutDescriptionShouldReturnSubCategoriesWithoutDescription(String description) {
        List<SubCategory> subcategoriesWithoutDescription = Arrays.asList(
                new SubCategory("jpa", "jpa", description, DISABLED, 1, category),
                new SubCategory("javaoo", "javaoo", description, ACTIVE, 1, category)
        );
        assertTrue(getSubCategoriesWithoutDescription(subCategories).containsAll(subcategoriesWithoutDescription));
    }
}
