import Logos.category.Category;
import Logos.subCategory.SubCategory;
import Logos.subCategory.enums.SubCategoryStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static Logos.subCategory.SubCategoryService.getQuantitySubCategoriesActivesWithDescription;
import static Logos.subCategory.SubCategoryService.getSubCategoriesWithoutDescription;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubCategoryServiceTest {
    private static Category category;
    private static List<SubCategory> subCategories;

    @BeforeAll
    public static void setUp() {
        category = new Category("programação", "programacao");
        subCategories = Arrays.asList(
                new SubCategory("Html", "html", "html", SubCategoryStatus.ACTIVE, 1, category),
                new SubCategory("javascript", "javascript", "javascript", SubCategoryStatus.DISABLED, 1, category),
                new SubCategory("jpa", "jpa", "", SubCategoryStatus.DISABLED, 1, category),
                new SubCategory("java", "java", null, SubCategoryStatus.ACTIVE, 1, category),
                new SubCategory("javaoo", "javaoo", "", SubCategoryStatus.ACTIVE, 1, category),
                new SubCategory("javaweb", "javaweb", "Java é uma ...", SubCategoryStatus.ACTIVE, 1, category));
    }
    @ParameterizedTest
    @ValueSource(longs = {3L})
    void getQuantitySubCategoriesActivesWithDescriptionShouldReturnTheQuantitySubCategoriesActivesWithDescription(long sizeExpected) {
        assertEquals(getQuantitySubCategoriesActivesWithDescription(subCategories) ,sizeExpected);
    }

    @ParameterizedTest
    @EmptySource
    void getSubCategoriesWithoutDescriptionShouldReturnSubCategoriesWithoutDescription(String description) {
        List<SubCategory> subcategoriesWithoutDescription = Arrays.asList(
                new SubCategory("jpa", "jpa", description, SubCategoryStatus.DISABLED, 1, category),
                new SubCategory("javaoo", "javaoo", description, SubCategoryStatus.ACTIVE, 1, category)
        );
        assertTrue(getSubCategoriesWithoutDescription(subCategories).containsAll(subcategoriesWithoutDescription));
    }
}
