package br.com.logos.course;

import br.com.logos.category.Category;
import br.com.logos.category.CategoryRepository;
import br.com.logos.subCategory.SubCategory;
import br.com.logos.subCategory.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;


@Controller
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(value = "/admin/courses/{categoryCode}/{subcategoryCode}")
    public String getAllCoursesPage(@PathVariable String categoryCode, @PathVariable String subcategoryCode,
                                    @PageableDefault(size = 5) Pageable pageable, Model model) {
        Optional<Category> possibleCategory = categoryRepository.findByCode(categoryCode);
        Optional<SubCategory> possibleSubCategory = subCategoryRepository.findByCode(subcategoryCode);
        if (possibleSubCategory.isEmpty() || possibleCategory.isEmpty()) {
            return "errors/notFound";
        }
        Page<Course> courses = courseRepository.findAllBySubCategory(possibleSubCategory.get(), pageable);
        model.addAttribute("courses", courses);
        model.addAttribute("subcategoryName", possibleSubCategory.get().getName());
        return "course/courseList";
    }
}
