package subCategory;

import Logos.category.Category;
import Logos.subCategory.SubCategory;
import Logos.subCategory.SubcategoryDao;
import Logos.subCategory.enums.SubCategoryStatus;
import Logos.utils.builder.CategoryBuilder;
import Logos.utils.builder.SubCategoryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static Logos.utils.JPAUtil.getEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

public class SubCategoryDaoTest {

    private SubcategoryDao dao;
    private static final EntityManager em = getEntityManager("test");
    private Category category;

    @BeforeEach
    void setUp() {
        this.dao = new SubcategoryDao(em);
        em.getTransaction().begin();
        category = new CategoryBuilder()
                .withName("Programação")
                .withCode("programacao")
                .withColorCode("#1C1C1C")
                .create();
        em.persist(category);
    }

    @Test
    void getAllActiveSubcategoriesShouldReturnOnlyActiveSubCategoriesByOrder() {
        SubCategory disabledSubcategory1 = new SubCategoryBuilder()
                .withName("python")
                .withCode("python")
                .withDescription("")
                .withStudyGuide("java...")
                .withStatus(SubCategoryStatus.DISABLED)
                .withOrder(1)
                .withCategory(category).create();

        SubCategory activeSubcategory1 = new SubCategoryBuilder()
                .withName("java")
                .withCode("java")
                .withDescription("java...")
                .withStudyGuide("java...")
                .withStatus(SubCategoryStatus.ACTIVE)
                .withOrder(1)
                .withCategory(category).create();

        SubCategory activeSubcategory2 = new SubCategoryBuilder()
                .withName("go")
                .withCode("go")
                .withDescription("go...")
                .withStudyGuide("go ...")
                .withStatus(SubCategoryStatus.ACTIVE)
                .withOrder(2)
                .withCategory(category).create();

        em.persist(disabledSubcategory1);
        em.persist(activeSubcategory1);
        em.persist(activeSubcategory2);

        assertThat(dao.getAllActiveSubcategories())
                .hasSize(2)
                .extracting(SubCategory::getCode)
                .containsExactly("java", "go")
                .doesNotContain("python");
    }

    @Test
    void getSubcategoriesWithoutDescriptionShouldReturnOnlySubCategoriesWithoutDescription() {
        SubCategory subCategoryDisabled1 = new SubCategoryBuilder()
                .withName("python")
                .withCode("python")
                .withDescription("")
                .withStudyGuide("java...")
                .withStatus(SubCategoryStatus.DISABLED)
                .withOrder(1)
                .withCategory(category).create();

        SubCategory subCategoryActive1 = new SubCategoryBuilder()
                .withName("java")
                .withCode("java")
                .withDescription("java...")
                .withStudyGuide("java...")
                .withStatus(SubCategoryStatus.ACTIVE)
                .withOrder(1)
                .withCategory(category).create();

        SubCategory subCategoryActive2 = new SubCategoryBuilder()
                .withName("python")
                .withCode("python")
                .withDescription("python...")
                .withStudyGuide("python ...")
                .withStatus(SubCategoryStatus.ACTIVE)
                .withOrder(2)
                .withCategory(category).create();

        em.persist(subCategoryDisabled1);
        em.persist(subCategoryActive1);
        em.persist(subCategoryActive2);
        assertThat(dao.getSubcategoriesWithoutDescription())
                .hasSize(1)
                .containsExactly("python")
                .doesNotContain("java", "go");
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
