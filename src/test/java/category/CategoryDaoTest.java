package category;

import Logos.category.Category;
import Logos.category.CategoryDao;
import Logos.category.enums.CategoryStatus;
import Logos.utils.builder.CategoryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static Logos.utils.JPAUtil.getEntityManagerTest;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CategoryDaoTest {

    private CategoryDao dao;

    private final static EntityManager em = getEntityManagerTest();

    @BeforeEach
    void setUp() {
        this.dao = new CategoryDao(em);
    }

    @Test
    void getAllActiveCategoriesShouldReturnOnlyActiveCategoriesByOrder() {
        Category activeCategory1 = new CategoryBuilder()
                .withName("Programação")
                .withCode("programacao")
                .withColorCode("#1C1C1C")
                .withOrder(1)
                .withStatus(CategoryStatus.ACTIVE)
                .create();

        Category activeCategory2 = new CategoryBuilder()
                .withName("Business")
                .withCode("business")
                .withColorCode("#1C1C1C")
                .withOrder(2)
                .withStatus(CategoryStatus.ACTIVE)
                .create();

        Category disabledCategory1 = new CategoryBuilder()
                .withName("Devops")
                .withCode("devops")
                .withColorCode("#1C1C1C")
                .withStatus(CategoryStatus.DISABLED)
                .create();

        em.getTransaction().begin();
        em.persist(activeCategory1);
        em.persist(activeCategory2);
        em.persist(disabledCategory1);

        assertThat(dao.getAllActiveCategories())
                .hasSize(2).extracting(Category::getCode)
                .containsExactly("programacao", "business")
                .doesNotContain("devops");
    }

    @AfterEach
    void rollback() {
        em.getTransaction().rollback();
    }

    @AfterAll
    static void close() {
        em.close();
    }
}
