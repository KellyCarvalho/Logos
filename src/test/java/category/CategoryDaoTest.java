package category;

import br.com.logos.category.Category;
import br.com.logos.category.CategoryDao;
import br.com.logos.category.enums.CategoryStatus;
import br.com.logos.utils.builder.CategoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static br.com.logos.utils.JPAUtil.getEntityManager;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CategoryDaoTest {

    private CategoryDao dao;

    private static EntityManager em;

    @BeforeEach
    void setUp() {
        em = getEntityManager("test");
        this.dao = new CategoryDao(em);
        em.getTransaction().begin();
    }

    @AfterEach
    void tearDown() {
        em.getTransaction().rollback();
        em.close();
    }

    @Test
    void getAllActiveCategoriesShouldReturnOnlyActiveCategoriesByOrder() {
        Category activeCategory1 = new CategoryBuilder()
                .withName("Programação")
                .withCode("programacao")
                .withColorCode("#1C1C1C")
                .withStudyGuide("guia de estudo")
                .withDescription("description")
                .withImageUrl("urlimage")
                .withOrder(1)
                .withStatus(CategoryStatus.ACTIVE)
                .create();

        Category activeCategory2 = new CategoryBuilder()
                .withName("Business")
                .withCode("business")
                .withColorCode("#1C1C1C")
                .withStudyGuide("guia de estudo")
                .withDescription("description")
                .withImageUrl("urlimage")
                .withOrder(2)
                .withStatus(CategoryStatus.ACTIVE)
                .create();

        Category disabledCategory1 = new CategoryBuilder()
                .withName("Devops")
                .withCode("devops")
                .withColorCode("#1C1C1C")
                .withStatus(CategoryStatus.DISABLED)
                .create();

        em.persist(activeCategory1);
        em.persist(activeCategory2);
        em.persist(disabledCategory1);

        assertThat(dao.getAllActiveCategories())
                .hasSize(2).extracting(Category::getCode)
                .containsExactly("programacao", "business")
                .doesNotContain("devops");
    }
}
