package subCategory;

import Logos.category.Category;
import Logos.subCategory.SubCategory;
import Logos.subCategory.SubcategoryDao;
import Logos.subCategory.enums.SubCategoryStatus;
import Logos.utils.builder.CategoryBuilder;
import Logos.utils.builder.SubCategoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static Logos.utils.JPAUtil.getEntityManagerTest;
import static org.assertj.core.api.Assertions.assertThat;

public class SubCategoryDaoTest {

    EntityManager em = getEntityManagerTest();
    SubcategoryDao dao;

    @BeforeEach
    void setUp(){
        this.dao = new SubcategoryDao(em);
        Category category = new CategoryBuilder()
                .withName("Programação")
                .withCode("programacao")
                .withColorCode("#1C1C1C")
                .create();

        SubCategory subCategoryPython = new SubCategoryBuilder()
                .withName("Python")
                .withCode("python")
                .withDescription("")
                .withStudyGuide("guia de estudo: ...")
                .withStatus(SubCategoryStatus.DISABLED)
                .withOrder(1)
                .withCategory(category).create();

        SubCategory subCategoryGo = new SubCategoryBuilder()
                .withName("Go")
                .withCode("go")
                .withDescription("go é uma linguagem...")
                .withStudyGuide("guia de estudo: ...")
                .withStatus(SubCategoryStatus.ACTIVE)
                .withOrder(1)
                .withCategory(category).create();

        SubCategory subCategoryJava = new SubCategoryBuilder()
                .withName("Java")
                .withCode("java")
                .withDescription("java é uma linguagem...")
                .withStudyGuide("guia de estudo: ...")
                .withStatus(SubCategoryStatus.ACTIVE)
                .withOrder(2)
                .withCategory(category).create();
        em.getTransaction().begin();
        em.persist(category);
        em.persist(subCategoryPython);
        em.persist(subCategoryGo);
        em.persist(subCategoryJava);
    }

    @Test
    void getAllActivesSubcategoriesShouldReturnOnlyActivesSubCategories(){
        List<SubCategory> subCategoriesActives = dao.getAllActivesSubcategories();
        assertThat(subCategoriesActives).hasSize(2).extracting(SubCategory::getCode).containsExactly("go","java");
    }

    @Test
    void getSubcategoriesWithoutDescriptionShouldReturnOnlySubCategoriesWithoutDescription(){
        List<String> subCategoriesWithoutDescription = dao.getSubcategoriesWithoutDescription();
        System.out.println(subCategoriesWithoutDescription);
        assertThat(subCategoriesWithoutDescription).hasSize(1).containsExactly("Python");
    }
}
