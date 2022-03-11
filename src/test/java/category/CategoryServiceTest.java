package category;

import Logos.category.Category;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import static Logos.category.CategoryService.getActiveCategories;
import static Logos.category.enums.CategoryStatus.ACTIVE;
import static Logos.category.enums.CategoryStatus.DISABLED;

public class CategoryServiceTest {

    @Test
    void getActiveCategoriesShouldReturnOnlyActivesCategories() {
        List<Category> categories = Arrays.asList(
                new Category("Devops", "devops", ACTIVE),
                new Category("Produtividade", "produtividade", DISABLED),
                new Category("Comunicação", "comunicacao", DISABLED));
        assertThat(getActiveCategories(categories))
                .hasSize(1)
                .extracting(Category::getCode)
                .containsExactly("devops");
    }
}
