import Logos.category.Category;
import Logos.category.enums.CategoryStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static Logos.category.Category.getActiveCategories;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {
    //TODO testar construtores
    //TODO se for apenas uma chamada colocar tudo em uma linha
    @ParameterizedTest
    @CsvSource({"Java", "J@v@", "ja va", "jáva"})
    void shouldThrowArgumentExceptionIfInvalidCode(String code) {
        assertThrows(IllegalArgumentException.class, () -> new Category("java", code));
    }

    //TODO fazer um teste que não gera exceção de Code
    @ParameterizedTest
    @NullAndEmptySource
    void constructorShouldThrowIllegalArgumentExceptionIfEmptyOrNullCode(String code) {
        assertThrows(IllegalArgumentException.class, () -> new Category("Java", code));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void constructorShouldThrowIllegalArgumentExceptionToEmptyOrNullName(String name) {
        assertThrows(IllegalArgumentException.class, () ->  new Category(name, "java"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"!191970", "#!78839", "#4545433"})
    void constructorShouldThrowIllegalArgumentExceptionIfColorCodeIsInvalid(String colorCode) {
        assertThrows(IllegalArgumentException.class,
                () -> { new Category("Java", "java", "java é legal", CategoryStatus.ACTIVE,
                            0, "umaurlqualquer.com.br", colorCode);
                });
    }

    //TODO especificar construtor
    @ParameterizedTest
    @ValueSource(strings = {"#191970", "#00008B", "#6495ED"})
    void constructorShouldNotThrowExceptionIfColorCodeIsValid(String colorCode) {
        assertDoesNotThrow(() -> {
            new Category("Java", "java", "java é legal", CategoryStatus.ACTIVE,
                    0, "umaurlqualquer.com.br", colorCode);
        });
    }

    //TODO fazer o caso de ter uma categoria desativada para verificar se retorna apenas as ativas e não contem a inativa
    //TODO Usar o contains
    @Test
    void getActiveCategoriesShouldReturnOnlyActivesCategories() {
        List<Category> categories = Arrays.asList(
                new Category("Programação", "programacao", CategoryStatus.ACTIVE),
                new Category("Devops", "devops", CategoryStatus.ACTIVE),
                new Category("Business", "business", CategoryStatus.ACTIVE));
        List<Category> categoriesActives = getActiveCategories(categories);
        assertEquals(categories, categoriesActives);
    }
}
