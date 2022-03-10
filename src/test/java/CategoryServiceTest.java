import Logos.category.Category;
import Logos.category.enums.CategoryStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static Logos.category.CategoryService.getActiveCategories;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoryServiceTest {
    public static List<Category> categories;
    @BeforeAll
    public static void setUp() {
        categories = Arrays.asList(
                new Category("Programação", "programacao", CategoryStatus.ACTIVE),
                new Category("Devops", "devops", CategoryStatus.ACTIVE),
                new Category("Business", "business", CategoryStatus.ACTIVE),
                new Category("Produtividade", "produtividade", CategoryStatus.DISABLED),
                new Category("Comunicação", "comunicacao", CategoryStatus.DISABLED));
    }
    @Test
    void getActiveCategoriesShouldReturnOnlyActivesCategories() {
        List<Category> categoriesTest = Arrays.asList(
                new Category("Programação", "programacao", CategoryStatus.ACTIVE),
                new Category("Devops", "devops", CategoryStatus.ACTIVE),
                new Category("Business", "business", CategoryStatus.ACTIVE));
        List<Category> categoriesActives = getActiveCategories(categories);
        assertTrue(categoriesActives.containsAll(categoriesTest));
    }

    @Test
    void getActiveCategoriesShouldNotReturnDisabledCategories() {
        List<Category> categories = Arrays.asList(
                new Category("Produtividade", "produtividade", CategoryStatus.DISABLED),
                new Category("Comunicação", "comunicacao", CategoryStatus.DISABLED)
        );
        List<Category> categoriesActives = getActiveCategories(categories);
        assertFalse(categoriesActives.containsAll(categories));
    }
}
