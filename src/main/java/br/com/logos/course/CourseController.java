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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
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

    @GetMapping("/admin/courses/new")
    public String viewFormInsertCategory(CourseInsertDTO courseInsertDTO, BindingResult result, Model model) {
        List<SubCategory> subcategories = subCategoryRepository.findAllByOrderByName();
        model.addAttribute("subcategories", subcategories);
        return "course/formInsertCourse";
    }

    @Transactional
    @PostMapping("/admin/courses/new")
    public String insert(@Valid CourseInsertDTO courseInsertDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return viewFormInsertCategory(courseInsertDTO, result, model);
        }
        Course course = courseInsertDTO.toEntity(courseInsertDTO);
        courseRepository.save(course);
        return "redirect:/admin/courses/" + course.getCategoryCode() + "/" + course.getSubCategoryCode();
    }


    @GetMapping("/admin/subcategories/{category}/{subcategory}/{course}")
    public String showFormUpdate(@PathVariable String category, @PathVariable String subcategory,
                                 @PathVariable String course, CourseUpdateDTO courseUpdateDto,
                                 BindingResult result, Model model) {
        Optional<Category> possibleCategory = categoryRepository.findByCode(category);
        Optional<SubCategory> possibleSubCategory = subCategoryRepository.findByCode(subcategory);
        Optional<Course> possibleCourse = courseRepository.findByCode(course);
        if (possibleSubCategory.isEmpty() || possibleCategory.isEmpty() || possibleCourse.isEmpty()) {
            return "errors/notFound";
        }
        List<SubCategory> subCategories = subCategoryRepository.findAllByOrderByName();
        model.addAttribute("courseUpdateDTO", result.hasErrors() ? courseUpdateDto : new CourseUpdateDTO(possibleCourse.get()));
        model.addAttribute("subCategories", subCategories);

        return "/course/formUpdateCourse";
    }

    @Transactional
    @PostMapping("/admin/subcategories/{category}/{subcategory}/{course}")
    public String update(@PathVariable String category, @PathVariable String subcategory,@PathVariable String course,
                         @Valid CourseUpdateDTO courseUpdateDTO, BindingResult result, Model model) {
        Optional<SubCategory> possibleSubCategory = subCategoryRepository.findByCode(subcategory);
        Optional<Category> possibleCategory = categoryRepository.findByCode(category);
        Optional<Course> possibleCourse = courseRepository.findByCode(course);

        if (possibleCategory.isEmpty() || possibleSubCategory.isEmpty() || possibleCourse.isEmpty()) {
            return "errors/notFound";
        }

        if (result.hasErrors()) {
            return showFormUpdate(category, subcategory, course ,courseUpdateDTO, result, model);
        }

        possibleCourse.get().update(courseUpdateDTO);
        return "redirect:/admin/courses/" + possibleCourse.get().getCategoryCode()+ "/" + possibleCourse.get().getSubCategoryCode();
    }

}
