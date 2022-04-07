package br.com.logos.category;

import br.com.logos.category.enums.CategoryStatus;
import br.com.logos.course.CourseRepository;
import br.com.logos.subCategory.SubCategoryRepository;
import br.com.logos.subCategory.enums.SubCategoryStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static br.com.logos.category.CategoryListDTO.toListCategoryDTO;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private CourseRepository courseRepository;

    //TODO dar suporte a XML e JSON
    //TODO colocar em outro controller
    @GetMapping(value = "/api/categories")
    @ResponseBody()
    public ResponseEntity<List<CategoryListDTO>> getActiveCategories() {
        return ResponseEntity.ok().body(toListCategoryDTO(
                categoryRepository
                .findCategoriesByStatusOrderByOrder(CategoryStatus.ACTIVE),
                courseRepository
                        .findByVisibilityAndSubCategory_Category_StatusOrderBySubCategory_Category_Order(true, CategoryStatus.ACTIVE),
                subCategoryRepository
                        .findAllByStatus(SubCategoryStatus.ACTIVE)));
    }

    @GetMapping("/admin/categories")
    public String getAllCategories(Model model) {
        List<Category> categories = categoryRepository.findByOrderByOrder();
        model.addAttribute("categories", categories);
        return "/categoriesList";
    }

    @GetMapping("/admin/categories/new")
    public String viewFormInsertCategory(CategoryInsertDTO categoryInsertDTO, BindingResult result) {
        return "/formInsertCategory";
    }

    @Transactional
    @PostMapping("/admin/categories/new")
    public String insert(@Valid CategoryInsertDTO categoryInsertDTO, BindingResult result) {
        if (result.hasErrors()) {
            return viewFormInsertCategory(categoryInsertDTO, result);
        }
        Category category = categoryInsertDTO.insertDTOtoEntity(categoryInsertDTO);
        categoryRepository.save(category);

        return "redirect:/admin/categories";
    }

    //TODO retornar um not found se o código não existir
    //TODO trocar o nome do paramentro para categoryCode
    @Transactional
    @PostMapping("/admin/categories/{code}")
    public String update(@PathVariable String code, @Valid CategoryUpdateDTO categoryUpdateDTO, BindingResult result, Model model) {
        if (result.hasErrors()){
            System.out.println(result.getAllErrors());
            return showCategory(code, categoryUpdateDTO, result, model);
        }
        Optional <Category> category = categoryRepository.findByCode(code);
        if (category.isEmpty()) {
            return "notFound";
        }
        categoryUpdateDTO.update(category.get());
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/{code}")
    public String showCategory(@PathVariable String code, CategoryUpdateDTO categoryUpdateDTO, BindingResult result, Model model) {
       Optional <Category> category = categoryRepository.findByCode(code);
        if (category.isEmpty()) {
            return "notFound";
        }
        model.addAttribute("categoryUpdateDTO", new CategoryUpdateDTO(category.get()));
        return "/formUpdateCategory";
    }
}
