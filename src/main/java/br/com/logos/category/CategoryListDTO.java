package br.com.logos.category;

import br.com.logos.course.Course;
import br.com.logos.course.CourseDTO;
import br.com.logos.subCategory.SubCategory;
import br.com.logos.subCategory.SubCategoryDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

import static br.com.logos.course.CourseDTO.getPublicCoursesByCategory;
import static br.com.logos.subCategory.SubCategoryDTO.getActiveSubcategoriesByCategory;

public class CategoryListDTO {

    @NotBlank(message = "Nome não pode estar em branco")
    @NotEmpty(message = "Nome não pode estar Vazio")
    @NotNull(message = "Nome não pode estar nulo")
    private String name;
    @NotBlank(message = "Código não pode estar em branco")
    @NotEmpty(message = "Código não pode estar Vazio")
    @NotNull(message = "Código não pode estar nulo")
    private String code;
    private int order;
    @Pattern(regexp = "^#([a-fA-F0-9]){3}(([a-fA-F0-9]){3})?$",message = "Deve ser uma cor válida")
    private String colorCode;
    private String studyGuide;
    private int quantityCoursesCategory;
    private List<CourseDTO> courses;
    private List<SubCategoryDTO> subCategories;

    public CategoryListDTO(String name, String code, int order, String colorCode, String studyGuide, List<CourseDTO> courses, List<SubCategoryDTO> subCategories) {
        this.name = name;
        this.code = code;
        this.order = order;
        this.colorCode = colorCode;
        this.studyGuide = studyGuide;
        this.quantityCoursesCategory = courses.size();
        this.courses = courses;
        this.subCategories = subCategories;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getOrder() {
        return order;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public String getColorCode() {
        return colorCode;
    }

    public int getQuantityCoursesCategory() {
        return quantityCoursesCategory;
    }

    public List<CourseDTO> getCourses() {
        return courses;
    }

    public List<SubCategoryDTO> getSubCategories() {
        return subCategories;
    }

    public void setQuantityCoursesCategory(int quantityCoursesCategory) {
        this.quantityCoursesCategory = quantityCoursesCategory;
    }

    public static List<CategoryListDTO> toListCategoryDTO(List<Category> categories, List<Course> courses, List<SubCategory> subCategories) {

        List<CategoryListDTO> categoriesDto = new ArrayList<>();

        categories.forEach(category -> {
            categoriesDto.add(new CategoryListDTO(category.getName(), category.getCode(), category.getOrder(),
                    category.getColorCode(), category.getStudyGuide(),
                    getPublicCoursesByCategory(courses, category.getId()), getActiveSubcategoriesByCategory(subCategories, category.getId())));
        });
        return categoriesDto;
    }
}
