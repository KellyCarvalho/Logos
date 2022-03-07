import Logos.category.Category;
import Logos.subCategory.SubCategory;
import Logos.subCategory.enums.SubCategoryStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.List;

import static Logos.subCategory.SubCategory.getQuantitySubCategoriesActivesWithDescription;
import static Logos.subCategory.SubCategory.getSubCategoriesWithoutDescription;
import static org.junit.jupiter.api.Assertions.*;

public class SubCategoryTest {
    private static Category category;

    @BeforeAll
    public static void initializeDependencies() {
        category = new Category("programação", "programacao");
    }

    @ParameterizedTest
    @CsvSource({"Java", "J@v@", "ja va", "jáva"})
    void shouldThrowArgumentExceptionIfSpecialCharactersSpacesAndLettersInUpperCaseInCode(String code) {
        assertThrows(IllegalArgumentException.class, () -> {
            new SubCategory("java", code, category);
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowIllegalArgumentExceptionIfEmptyOrNullCode(String code) {
        assertThrows(IllegalArgumentException.class, () -> {
            new SubCategory("java", code, category);
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowIllegalArgumentExceptionIfEmptyOrNullName(String name) {
        assertThrows(IllegalArgumentException.class, () -> {
            new SubCategory(name, "java", category);
        });
    }

    @ParameterizedTest
    @NullSource
    void shouldThrowIllegalArgumentExceptionToEmptyOrNullCategory(Category category) {
        assertThrows(IllegalArgumentException.class, () -> {
            new SubCategory("java", "java", category);
        });
    }

    @Test
    void isActiveShouldReturnTrueIfStatusIsActive() {
        SubCategory subCategory = new SubCategory("java", "java", "entendendo o java",
                SubCategoryStatus.ACTIVE, 1, category);
        assertTrue(subCategory.isActive());
    }

    @Test
    void isActiveShouldReturnFalseIfStatusIsDisabled() {
        SubCategory subCategory = new SubCategory("java", "java", "entendendo o java",
                SubCategoryStatus.DISABLED, 1, category);
        assertFalse(subCategory.isActive());
    }

    @ParameterizedTest
    @EmptySource
    void isEmptyDescriptionShouldReturnTrueIfDescriptionIsEmpty(String description) {
        SubCategory subCategory = new SubCategory("java", "java", description,
                SubCategoryStatus.DISABLED, 1, category);
        assertTrue(subCategory.isEmptyDescription());
    }

    @Test
    void isEmptyDescriptionShouldReturnFalseIfDescriptionIsNotEmpty() {
        SubCategory subCategory = new SubCategory("java", "java", "java é legal",
                SubCategoryStatus.DISABLED, 1, category);
        assertFalse(subCategory.isEmptyDescription());
    }

    @ParameterizedTest
    @ValueSource(strings = "Java é uma ...")
    void getQuantitySubCategoriesActivesWithDescriptionShouldReturnAItQuantity(String description) {
        List<SubCategory> categories = Arrays.asList(
                new SubCategory("java", "java", description,
                        SubCategoryStatus.DISABLED, 1, category),
                new SubCategory("java2", "java2", description,
                        SubCategoryStatus.ACTIVE, 1, category));

        assertTrue(getQuantitySubCategoriesActivesWithDescription(categories) == 1L);
    }

    @Test
    void getSubCategoriesWithoutDescriptionShouldReturnAItList() {
        List<SubCategory> categories = Arrays.asList(
                new SubCategory("java", "java", "",
                        SubCategoryStatus.ACTIVE, 1, category),
                new SubCategory("java2", "java2", "",
                        SubCategoryStatus.ACTIVE, 1, category));

        List<SubCategory> categoriesActives = getSubCategoriesWithoutDescription(categories);
        assertEquals(categories, categoriesActives);
    }
}
