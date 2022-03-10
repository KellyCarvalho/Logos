import Logos.category.Category;
import Logos.category.enums.CategoryStatus;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static Logos.category.CategoryService.getActiveCategories;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoryServiceTest {

    @Test
    void getActiveCategoriesShouldReturnOnlyActivesCategories() {
        List<Category> categories = Arrays.asList(
                new Category("Programação", "programacao", CategoryStatus.ACTIVE),
                new Category("Devops", "devops", CategoryStatus.ACTIVE),
                new Category("Business", "business", CategoryStatus.ACTIVE),
                new Category("Produtividade", "produtividade", CategoryStatus.DISABLED),
                new Category("Comunicação", "comunicacao", CategoryStatus.DISABLED));

        List<Category> activeCategories = Arrays.asList(
                new Category("Programação", "programacao", CategoryStatus.ACTIVE),
                new Category("Devops", "devops", CategoryStatus.ACTIVE),
                new Category("Business", "business", CategoryStatus.ACTIVE)
        );
        List<Category> categoriesActives = getActiveCategories(categories);
        assertTrue(categoriesActives.containsAll(activeCategories));
    }

    @Test
    void getActiveCategoriesShouldNotReturnDisabledCategories() {
        List<Category> categories = Arrays.asList(
                new Category("Programação", "programacao", CategoryStatus.ACTIVE),
                new Category("Devops", "devops", CategoryStatus.ACTIVE),
                new Category("Business", "business", CategoryStatus.ACTIVE),
                new Category("Produtividade", "produtividade", CategoryStatus.DISABLED),
                new Category("Comunicação", "comunicacao", CategoryStatus.DISABLED));

        List<Category> disabledCategories = Arrays.asList(
                new Category("Produtividade", "produtividade", CategoryStatus.DISABLED),
                new Category("Comunicação", "comunicacao", CategoryStatus.DISABLED)
        );
        List<Category> categoriesActives = getActiveCategories(categories);
        assertFalse(categoriesActives.containsAll(disabledCategories));
    }
}
