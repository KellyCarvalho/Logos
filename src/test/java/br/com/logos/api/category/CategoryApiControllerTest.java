package br.com.logos.api.category;

import br.com.logos.category.Category;
import br.com.logos.category.CategoryRepository;
import br.com.logos.category.enums.CategoryStatus;
import br.com.logos.course.Course;
import br.com.logos.course.CourseRepository;
import br.com.logos.subCategory.SubCategory;
import br.com.logos.subCategory.SubCategoryRepository;
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

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

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
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void shouldReturnAllCategoriesWithActiveCoursesAndSubCategories() throws Exception {
        //TODO não precisa da categoria de indice 0, colocar a última categoria como disable, testar todos os cenários criando o mínimo de objetos possíveis
        List<Category> categories = Arrays.asList(
                new CategoryBuilder()
                        .withName("Business")
                        .withCode("business")
                        .withStatus(CategoryStatus.DISABLED)
                        .withOrder(0)
                        .withColorCode("#ff8c2a")
                        .create(),
                new CategoryBuilder()
                        .withName("Programação")
                        .withCode("programacao")
                        .withStatus(CategoryStatus.ACTIVE)
                        .withOrder(2)
                        .withColorCode("#00c86f")
                        .create(),
                new CategoryBuilder()
                        .withName("DevOps")
                        .withCode("devops")
                        .withStatus(CategoryStatus.ACTIVE)
                        .withOrder(1)
                        .withColorCode("#f16165")
                        .create());

        categoryRepository.saveAll(categories);

        List<SubCategory> subCategories = Arrays.asList(
                new SubCategoryBuilder()
                        .withCode("java")
                        .withName("Java")
                        .withStatus(SubCategoryStatus.ACTIVE)
                        .withOrder(1)
                        .withCategory(categories.get(1))
                        .create(),
                new SubCategoryBuilder()
                        .withCode("java-e-persistencia")
                        .withName("Java e Persistência")
                        .withStatus(SubCategoryStatus.DISABLED)
                        .withOrder(2)
                        .withCategory(categories.get(1))
                        .create(),
                new SubCategoryBuilder()
                        .withCode("builds-e-controle-de-versao")
                        .withName("Builds e Controle de versão")
                        .withStatus(SubCategoryStatus.ACTIVE)
                        .withCategory(categories.get(2))
                        .create());

        subCategoryRepository.saveAll(subCategories);

        List<Course> courses = Arrays.asList(
                new CourseBuilder().withCode("java-oo")
                        .withName("Java OO: Introdução à Orientação a Objetos")
                        .withEstimatedTime(8)
                        .withInstructorName("Paulo Silveira")
                        .withVisibility(true)
                        .withSubCategory(subCategories.get(0))
                        .create(),
                new CourseBuilder().withCode("jpa")
                        .withName("Jpa")
                        .withEstimatedTime(8)
                        .withInstructorName("Paulo Silveira")
                        .withVisibility(true)
                        .withSubCategory(subCategories.get(1))
                        .create(),
                new CourseBuilder().withCode("git-e-github")
                        .withName("Git e Github")
                        .withEstimatedTime(9)
                        .withInstructorName("Thais")
                        .withVisibility(true)
                        .withSubCategory(subCategories.get(2))
                        .create());

        courseRepository.saveAll(courses);
        //TODo adicionar o código da subcategoria tbm
        mockMvc.perform(get("/api/categories")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*").isNotEmpty())
                .andExpect(jsonPath("$[0].name").value("DevOps"))
                .andExpect(jsonPath("$[0].code").value("devops"))
                .andExpect(jsonPath("$[0].order").value(1))
                .andExpect(jsonPath("$[0].colorCode").value("#f16165"))
                .andExpect(jsonPath("$[0].courses[0].code").value("git-e-github"))
                .andExpect(jsonPath("$[0].quantityCoursesCategory").value(1))
                .andExpect(jsonPath("$[1].name").value("Programação"))
                .andExpect(jsonPath("$[1].code").value("programacao"))
                .andExpect(jsonPath("$[1].order").value(2))
                .andExpect(jsonPath("$[1].colorCode").value("#00c86f"))
                .andExpect(jsonPath("$[1].courses[0].code").value("java-oo"))
                .andExpect(jsonPath("$[1].courses[1].code").value("jpa"))
                .andExpect(jsonPath("$[1].quantityCoursesCategory").value(2));
    }
}
