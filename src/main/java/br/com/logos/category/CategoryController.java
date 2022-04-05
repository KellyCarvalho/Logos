package br.com.logos.category;

import br.com.logos.category.enums.CategoryStatus;
import br.com.logos.course.Course;
import br.com.logos.course.CourseDTO;
import br.com.logos.course.CourseRepository;
import br.com.logos.subCategory.SubCategory;
import br.com.logos.subCategory.SubCategoryDTO;
import br.com.logos.subCategory.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/api/categories")
    @ResponseBody
    public ResponseEntity<List<CategoryListDTO>> getActiveCategories() {
        return ResponseEntity.ok().body(toListCategoryDTO(categoryRepository.findCategoriesByStatusOrderByOrder(CategoryStatus.ACTIVE)));
    }

    @GetMapping("/admin/categories")
    public String getAllCategories(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "/categoriesList";
    }

    @GetMapping("/admin/categories/new")
    public String viewFormInsertCategory(CategoryInsertDTO categoryInsertDTO, BindingResult result) {
        return "/formInsertCategory";
    }

    @PostMapping("/admin/categories")
    public String insert(@Valid CategoryInsertDTO categoryInsertDTO, BindingResult result) {
        if (result.hasFieldErrors()) {
            viewFormInsertCategory(categoryInsertDTO, result);
        }
        if (categoryInsertDTO.getStatus() == null) {
            categoryInsertDTO.setStatus(CategoryStatus.DISABLED);
        }
        Category category = insertDTOtoCategory(categoryInsertDTO);
        categoryRepository.save(category);

        return "redirect:/admin/categories";
    }

    @PostMapping("/admin/categories/{code}")
    public String update(@Valid CategoryUpdateDTO categoryUpdateDTO, @PathVariable String code) {
        if (categoryUpdateDTO.getStatus() == null) {
            categoryUpdateDTO.setStatus(CategoryStatus.DISABLED);
        }
        if (categoryRepository.findCategoryByCode(code) != null) {
            Category category = updateDTOtoCategory(categoryUpdateDTO);
            category.setId(categoryUpdateDTO.getId());
            categoryRepository.save(category);
        }
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/{code}")
    public String showCategory(@PathVariable String code, Model model) {
        Category category = categoryRepository.findCategoryByCode(code);
        model.addAttribute("category", category);
        return "/formUpdateCategory";
    }


    private List<CategoryListDTO> toListCategoryDTO(List<Category> categories) {
        List<Course> courses = courseRepository.findByVisibilityAndSubCategory_Category_StatusOrderBySubCategory_Category_Order(true, CategoryStatus.ACTIVE);
        List<SubCategory> subCategories = subCategoryRepository.findAll();

        List<CategoryListDTO> categoriesDto = new ArrayList<>();

        categories.forEach(category -> {
            categoriesDto.add(new CategoryListDTO(category.getName(), category.getCode(), category.getOrder(),
                    category.getColorCode(), category.getStudyGuide(), courseRepository.findBySubCategory_Category_Id(category.getId()).size(),
                    addCoursesToCategoriesList(courses, category.getId()), addSubCategoriesToCategoriesList(subCategories, category.getId())));
        });
        return categoriesDto;
    }

    private List<CourseDTO> addCoursesToCategoriesList(List<Course> courses, Long id) {
        List<CourseDTO> coursesDTOs = new ArrayList<>();
        courses.forEach(course -> {
            if (course.getCategoryId() == id) {
                coursesDTOs.add(new CourseDTO(course.getName(), course.getCode(), course.getEstimatedTime(), course.getDevelopedSkills()));
            }
        });
        return coursesDTOs;
    }

    private List<SubCategoryDTO> addSubCategoriesToCategoriesList(List<SubCategory> subCategories, Long id) {
        List<SubCategoryDTO> subCategoriesDTO = new ArrayList<>();
        subCategories.forEach(subCategory -> {
            if (subCategory.getCategoryId() == id) {
                subCategoriesDTO.add(new SubCategoryDTO(subCategory.getName(), subCategory.getCode(), subCategory.getStudyGuide()));

            }
        });
        return subCategoriesDTO;
    }

    private Category insertDTOtoCategory(CategoryInsertDTO categoryInsertDTO) {
        return new Category(categoryInsertDTO.getName(), categoryInsertDTO.getCode(), categoryInsertDTO.getDescription(),
                categoryInsertDTO.getStudyGuide(), categoryInsertDTO.getStatus(), categoryInsertDTO.getOrder(),
                categoryInsertDTO.getImageUrl(), categoryInsertDTO.getColorCode());
    }

    private Category updateDTOtoCategory(CategoryUpdateDTO categoryUpdateDTO) {
        return new Category(categoryUpdateDTO.getName(), categoryUpdateDTO.getCode(), categoryUpdateDTO.getDescription(),
                categoryUpdateDTO.getStudyGuide(), categoryUpdateDTO.getStatus(), categoryUpdateDTO.getOrder(),
                categoryUpdateDTO.getImageUrl(), categoryUpdateDTO.getColorCode());
    }
}
