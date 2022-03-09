import Logos.category.Category;
import Logos.subCategory.SubCategory;
import Logos.subCategory.enums.SubCategoryStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.List;

import static Logos.subCategory.SubCategory.getQuantitySubCategoriesActivesWithDescription;
import static Logos.subCategory.SubCategory.getSubCategoriesWithoutDescription;
import static org.junit.jupiter.api.Assertions.*;

public class SubCategoryTest {
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
    @CsvSource({"Java", "J@v@", "ja va", "jáva"})
    void constructorShouldThrowArgumentExceptionIfInvalidCode(String code) {
        assertThrows(IllegalArgumentException.class, () -> {
            new SubCategory("java", code, category);
        });
    }

    @ParameterizedTest
    @CsvSource({"java", "java-oo","java-","j-v"})
    void constructorShouldNotThrowExceptionIfValidCode(String code) {
        assertDoesNotThrow( () -> { new SubCategory("java", code, category);});
    }


    @ParameterizedTest
    @NullAndEmptySource
    void ConstructorShouldThrowIllegalArgumentExceptionIfEmptyOrNullCode(String code) {
        assertThrows(IllegalArgumentException.class, () -> {
            new SubCategory("java", code, category);
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    void ConstructorShouldThrowIllegalArgumentExceptionIfEmptyOrNullName(String name) {
        assertThrows(IllegalArgumentException.class, () -> {
            new SubCategory(name, "java", category);
        });
    }

    @ParameterizedTest
    @CsvSource({"Java", "Java oo","java"})
    void ConstructorShouldNotThrowIllegalArgumentExceptionIfIsNotEmptyOrNullName(String name) {
        assertDoesNotThrow( () -> {
            new SubCategory(name, "java", category);
        });
    }

    @ParameterizedTest
    @NullSource
    void ConstructorShouldThrowIllegalArgumentExceptionToEmptyOrNullCategory(Category category) {
        assertThrows(IllegalArgumentException.class, () -> {
            new SubCategory("java", "java", category);
        });
    }

    @ParameterizedTest
    @ValueSource(longs = {3L})
    void getQuantitySubCategoriesActivesWithDescriptionShouldReturnTheQuantitySubCategoriesActivesWithDescription(long sizeExpected) {
        assertTrue(getQuantitySubCategoriesActivesWithDescription(subCategories) == sizeExpected);
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
