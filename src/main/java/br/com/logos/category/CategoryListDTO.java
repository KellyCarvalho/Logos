package br.com.logos.category;

import br.com.logos.course.Course;
import br.com.logos.course.CourseDTO;
import br.com.logos.subCategory.SubCategory;
import br.com.logos.subCategory.SubCategoryDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.List;

import static br.com.logos.course.CourseDTO.getPublicCoursesByCategory;
import static br.com.logos.subCategory.SubCategoryDTO.getActiveSubcategoriesByCategory;

@Getter
@Setter
@ToString
public class CategoryListDTO {

    @NotBlank(message = "Nome não pode estar em branco")
    private String name;
    @NotBlank(message = "Código não pode estar em branco")
    @Pattern(regexp = "[[a-z-]+]+", message = "Código  inválido, não pode ter caracteres especiais ou números, apenas o hífem é permitido, letras devem ser minúsculas")
    private String code;
    @PositiveOrZero(message = "Ordem deve ter valor positivo ou 0")
    private int order;
    @Pattern(regexp = "^#([a-fA-F0-9]){6}?$|^[\s]*$", message = "cor inválida")
    private String colorCode;
    private String studyGuide;
    private int quantityCoursesCategory;
    private List<CourseDTO> courses;
    private List<SubCategoryDTO> subCategories;

    @Deprecated
    public CategoryListDTO(){}

    public CategoryListDTO(Category category, List<CourseDTO> courses, List<SubCategoryDTO> subCategories) {
        this.name = category.getName();
        this.code = category.getCode();
        this.order = category.getOrder();
        this.colorCode = category.getColorCode();
        this.studyGuide = category.getStudyGuide();
        this.quantityCoursesCategory = courses.size();
        this.courses = courses;
        this.subCategories = subCategories;
    }

    public static List<CategoryListDTO> toListCategoryDTO(List<Category> categories, List<Course> courses, List<SubCategory> subCategories) {

        List<CategoryListDTO> categoriesDto = new ArrayList<>();

        categories.forEach(category -> {
            categoriesDto.add(new CategoryListDTO(category,
                    getPublicCoursesByCategory(courses, category.getId()), getActiveSubcategoriesByCategory(subCategories, category.getId())));
        });
        return categoriesDto;
    }
}
