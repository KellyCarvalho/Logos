package br.com.logos.category;

import org.springframework.beans.factory.annotation.Autowired;
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
public class CategoryAdminController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/admin/categories")
    public String getAllCategories(Model model) {
        List<Category> categories = categoryRepository.findByOrderByOrder();
        model.addAttribute("categories", categories);

        return "category/categoriesList";
    }

    @GetMapping("/admin/categories/new")
    public String viewFormInsertCategory(CategoryInsertDTO categoryInsertDTO, BindingResult result, Model model) {
        return "category/formInsertCategory";
    }

    @Transactional
    @PostMapping("/admin/categories/new")
    public String insert(@Valid CategoryInsertDTO categoryInsertDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return viewFormInsertCategory(categoryInsertDTO, result, model);
        }
        Category category = categoryInsertDTO.insertDTOtoEntity(categoryInsertDTO);
        categoryRepository.save(category);

        return "redirect:/admin/categories";
    }

    @Transactional
    @PostMapping("/admin/categories/{categoryCode}")
    public String update(@PathVariable String categoryCode, @Valid CategoryUpdateDTO categoryUpdateDTO, BindingResult result, Model model) {
        Optional<Category> category = categoryRepository.findByCode(categoryCode);

        if (category.isEmpty()) {
            return "notFound";
        }

        if (result.hasErrors()) {
            model.addAttribute("categoryUpdateDTO", result.hasErrors() ? categoryUpdateDTO : new CategoryUpdateDTO(category.get()));
            return "category/formUpdateCategory";
        }

        categoryUpdateDTO.update(category.get());

        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/{code}")
    public String showCategory(@PathVariable String code, CategoryUpdateDTO categoryUpdateDTO, BindingResult result, Model model) {
        Optional<Category> category = categoryRepository.findByCode(code);
        if (category.isEmpty()) {
            return "notFound";
        }
        model.addAttribute("categoryUpdateDTO", new CategoryUpdateDTO(category.get()));

        return "category/formUpdateCategory";
    }
}
