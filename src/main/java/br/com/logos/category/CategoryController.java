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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
    public ModelAndView getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("/categoriesList");
        modelAndView.addObject("categories",categories);
        return modelAndView;
    }

    @GetMapping("/admin/categories/new")
    public ModelAndView viewFormCreateCategory(){
        ModelAndView modelAndView = new ModelAndView("/formCreateCategory");
        return modelAndView;
    }

    @PostMapping("/admin/categories")
    public ResponseEntity<Category> insert(@Valid CategoryInsertDTO categoryInsertDTO, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder){
        if (bindingResult.hasErrors()){
            ResponseEntity.badRequest();
        }
        Category category = toCategory(categoryInsertDTO);
         categoryRepository.save(category);
        URI uri = uriComponentsBuilder.path("category/{id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).body(category);
    }

    private List<CategoryListDTO> toListCategoryDTO(List<Category> categories) {
        List<Course> courses = courseRepository.findCoursesByVisibilityAndSubCategoryCategoryStatusOrderBySubCategoryCategoryOrder(true, CategoryStatus.ACTIVE);
        List<SubCategory> subCategories = subCategoryRepository.findAll();

        List<CategoryListDTO> categoriesDto = new ArrayList<>();

        categories.forEach(category -> {
            categoriesDto.add(new CategoryListDTO(category.getName(), category.getCode(), category.getOrder(),
                    category.getColorCode(), category.getStudyGuide(), courseRepository.findAllBySubCategoryCategoryId(category.getId()).size(),
                    addCoursesToCategoriesList(courses, category.getId()), addSubCategoriesToCategoriesList(subCategories, category.getId())));
        });
        return categoriesDto;
    }

    private List<CourseDTO> addCoursesToCategoriesList(List<Course> courses, Long id) {
        List<CourseDTO> coursesDTOs = new ArrayList<>();
        courses.forEach(course -> {
            if (course.getSubCategory().getCategory().getId() == id) {
                coursesDTOs.add(new CourseDTO(course.getName(), course.getCode(), course.getEstimatedTime(), course.getDevelopedSkills()));
            }
        });
        return coursesDTOs;
    }

    private List<SubCategoryDTO> addSubCategoriesToCategoriesList(List<SubCategory> subCategories, Long id) {
        List<SubCategoryDTO> subCategoriesDTO = new ArrayList<>();
        subCategories.forEach(subCategory -> {
            if (subCategory.getCategory().getId() == id) {
                subCategoriesDTO.add(new SubCategoryDTO(subCategory.getName(), subCategory.getCode(), subCategory.getStudyGuide()));

            }
        });
        return subCategoriesDTO;
    }

    private Category toCategory(CategoryInsertDTO categoryInsertDTO) {
        return new Category(categoryInsertDTO.getName(), categoryInsertDTO.getCode(), categoryInsertDTO.getDescription(),
                categoryInsertDTO.getStudyGuide(), categoryInsertDTO.getStatus(), categoryInsertDTO.getOrder(),
                categoryInsertDTO.getImageUrl(), categoryInsertDTO.getColorCode());
    }
}
