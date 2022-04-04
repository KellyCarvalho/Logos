package subCategory;

import br.com.logos.category.Category;
import br.com.logos.subCategory.SubCategory;
import br.com.logos.subCategory.SubcategoryDao;
import br.com.logos.subCategory.enums.SubCategoryStatus;
import br.com.logos.utils.builder.CategoryBuilder;
import br.com.logos.utils.builder.SubCategoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static br.com.logos.utils.JPAUtil.getEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

public class SubCategoryDaoTest {

    private SubcategoryDao dao;
    private static EntityManager em;
    private Category category;

    @BeforeEach
    void setUp() {
        em = getEntityManager("test");
        this.dao = new SubcategoryDao(em);
        em.getTransaction().begin();
        category = new CategoryBuilder()
                .withName("Programação")
                .withCode("programacao")
                .withColorCode("#1C1C1C")
                .create();
        em.persist(category);
    }

    @AfterEach
    void tearDown() {
        em.getTransaction().rollback();
        em.close();
    }

    @Test
    void getAllActiveSubcategoriesShouldReturnOnlyActiveSubCategoriesByOrder() {
        SubCategory disabledSubcategory1 = new SubCategoryBuilder()
                .withName("python")
                .withCode("python")
                .withDescription("")
                .withStudyGuide("python...")
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
        SubCategory subCategoryWithoutDescription1 = new SubCategoryBuilder()
                .withName("python")
                .withCode("python")
                .withDescription("")
                .withStudyGuide("python...")
                .withStatus(SubCategoryStatus.DISABLED)
                .withOrder(1)
                .withCategory(category).create();

        SubCategory subCategoryWithoutDescription2 = new SubCategoryBuilder()
                .withName("go")
                .withCode("go")
                .withStudyGuide("go ...")
                .withStatus(SubCategoryStatus.ACTIVE)
                .withOrder(2)
                .withCategory(category).create();

        SubCategory subCategoryWithDescription1 = new SubCategoryBuilder()
                .withName("java")
                .withCode("java")
                .withDescription("java...")
                .withStudyGuide("java...")
                .withStatus(SubCategoryStatus.ACTIVE)
                .withOrder(1)
                .withCategory(category).create();

        em.persist(subCategoryWithoutDescription1);
        em.persist(subCategoryWithDescription1);
        em.persist(subCategoryWithoutDescription2);
        assertThat(dao.getSubcategoriesWithoutDescription())
                .hasSize(2)
                .containsExactly("python", "go")
                .doesNotContain("java");
    }
}
