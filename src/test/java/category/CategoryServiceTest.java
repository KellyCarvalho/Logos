package category;

import Logos.category.Category;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static Logos.category.CategoryService.getActiveCategories;
import static Logos.category.enums.CategoryStatus.ACTIVE;
import static Logos.category.enums.CategoryStatus.DISABLED;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoryServiceTest {

    @Test
    void getActiveCategoriesShouldReturnOnlyActivesCategories() {
        List<Category> categories = Arrays.asList(
                new Category("Programação", "programacao", ACTIVE),
                new Category("Devops", "devops", ACTIVE),
                new Category("Business", "business", ACTIVE),
                new Category("Produtividade", "produtividade", DISABLED),
                new Category("Comunicação", "comunicacao", DISABLED));

        List<Category> activeCategories = Arrays.asList(
                new Category("Programação", "programacao", ACTIVE),
                new Category("Devops", "devops", ACTIVE),
                new Category("Business", "business", ACTIVE)
        );
        List<Category> categoriesActives = getActiveCategories(categories);
        assertTrue(categoriesActives.containsAll(activeCategories));
    }

    @Test
    void getActiveCategoriesShouldNotReturnDisabledCategories() {
        List<Category> categories = Arrays.asList(
                new Category("Programação", "programacao", ACTIVE),
                new Category("Devops", "devops", ACTIVE),
                new Category("Business", "business", ACTIVE),
                new Category("Produtividade", "produtividade", DISABLED),
                new Category("Comunicação", "comunicacao", DISABLED));

        List<Category> disabledCategories = Arrays.asList(
                new Category("Produtividade", "produtividade", DISABLED),
                new Category("Comunicação", "comunicacao", DISABLED)
        );
        List<Category> categoriesActives = getActiveCategories(categories);
        assertFalse(categoriesActives.containsAll(disabledCategories));
    }
}
