package category;

import Logos.category.Category;
import Logos.category.CategoryDao;
import Logos.category.enums.CategoryStatus;
import Logos.utils.builder.CategoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import java.util.List;

import static Logos.utils.JPAUtil.getEntityManagerTest;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CategoryDaoTest {

    EntityManager em = getEntityManagerTest();
    CategoryDao dao;

    @BeforeEach
    void setUp() {
        this.dao = new CategoryDao(em);
        Category categoryProgramacao = new CategoryBuilder()
                .withName("Programação")
                .withCode("programacao")
                .withColorCode("#1C1C1C")
                .withOrder(1)
                .withStatus(CategoryStatus.ACTIVE)
                .create();
        Category categoryBusiness = new CategoryBuilder()
                .withName("Business")
                .withCode("business")
                .withColorCode("#1C1C1C")
                .withOrder(2)
                .withStatus(CategoryStatus.ACTIVE)
                .create();
        Category categoryDevops = new CategoryBuilder()
                .withName("Devops")
                .withCode("devops")
                .withColorCode("#1C1C1C")
                .withStatus(CategoryStatus.DISABLED)
                .create();
        em.getTransaction().begin();
        em.persist(categoryProgramacao);
        em.persist(categoryBusiness);
        em.persist(categoryDevops);
    }

    @Test
    void getAllActivesCategoriesShouldReturnOnlyActivesCategories() {
        List<Category> categoriesActives = dao.getAllActivesCategories();
        assertThat(categoriesActives)
                .hasSize(2).extracting(Category::getCode)
                .containsExactly("programacao", "business");
    }
}
