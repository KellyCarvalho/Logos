package br.com.logos.api.category;

import br.com.logos.category.Category;
import br.com.logos.category.enums.CategoryStatus;
import br.com.logos.course.Course;
import br.com.logos.subCategory.SubCategory;
import br.com.logos.subCategory.enums.SubCategoryStatus;
import br.com.logos.utils.builders.CategoryBuilder;
import br.com.logos.utils.builders.CourseBuilder;
import br.com.logos.utils.builders.SubCategoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CategoryApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EntityManager em;

    @Test
    public void shouldReturnAllCategoriesWithVisibleCoursesAndActiveSubCategories() throws Exception {
        Category activeCategory = new CategoryBuilder()
                .withName("Programação")
                .withCode("programacao")
                .withStatus(CategoryStatus.ACTIVE)
                .withOrder(0)
                .withColorCode("#00c86f")
                .create();

        Category disabledCategory = new CategoryBuilder()
                .withName("DevOps")
                .withCode("devops")
                .withOrder(1)
                .withColorCode("#f16165")
                .create();

        SubCategory activeSubCategoryZero = new SubCategoryBuilder()
                .withCode("java")
                .withName("Java")
                .withStatus(SubCategoryStatus.ACTIVE)
                .withOrder(1)
                .withCategory(activeCategory)
                .create();

        SubCategory disabledSubCategory = new SubCategoryBuilder()
                .withCode("builds-e-controle-de-versao")
                .withName("Builds e Controle de versão")
                .withStatus(SubCategoryStatus.ACTIVE)
                .withCategory(disabledCategory)
                .create();

        Course visibleCourseZero = new CourseBuilder()
                .withCode("java-oo")
                .withName("Java OO: Introdução à Orientação a Objetos")
                .withEstimatedTime(8)
                .withInstructorName("Paulo Silveira")
                .withVisibility(true)
                .withSubCategory(activeSubCategoryZero)
                .create();

        Course visibleCourseOne = new CourseBuilder()
                .withCode("gitlab")
                .withName("GitLab")
                .withEstimatedTime(8)
                .withInstructorName("Paulo Silveira")
                .withVisibility(true)
                .withSubCategory(disabledSubCategory)
                .create();

        Course noVisibleCourseZero = new CourseBuilder()
                .withCode("java-iniciante")
                .withName("Java Iniciante")
                .withEstimatedTime(8)
                .withInstructorName("Paulo Silveira")
                .withSubCategory(activeSubCategoryZero)
                .create();

        Course noVisibleCourseOne = new CourseBuilder()
                .withCode("git-e-github")
                .withName("Git e Github")
                .withEstimatedTime(9)
                .withInstructorName("Madu")
                .withSubCategory(disabledSubCategory)
                .create();

        em.persist(activeCategory);
        em.persist(disabledCategory);
        em.persist(activeSubCategoryZero);
        em.persist(disabledSubCategory);
        em.persist(visibleCourseZero);
        em.persist(visibleCourseOne);
        em.persist(noVisibleCourseZero);
        em.persist(noVisibleCourseOne);

        mockMvc.perform(get("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].quantityCoursesCategory").value(1))
                .andExpect(jsonPath("$[0].name").value("Programação"))
                .andExpect(jsonPath("$[0].code").value("programacao"))
                .andExpect(jsonPath("$[0].order").value(0))
                .andExpect(jsonPath("$[0].colorCode").value("#00c86f"))
                .andExpect(jsonPath("$[0].courses[0].code").value("java-oo"))
                .andExpect(jsonPath("$[0].subCategories[0].code").value("java"))
                .andExpect(jsonPath("$[0].quantityCoursesCategory").value(1));
    }
}
