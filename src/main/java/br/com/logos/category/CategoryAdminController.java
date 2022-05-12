package br.com.logos.category;

import br.com.logos.category.validator.CategoryInsertValidator;
import br.com.logos.category.validator.CategoryUpdateValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class CategoryAdminController {

    private final CategoryRepository categoryRepository;

    private final CategoryInsertValidator categoryInsertValidator;

    private final CategoryUpdateValidator categoryUpdateValidator;

    @InitBinder({"categoryInsertDTO"})
    void initBinderInsertDto(WebDataBinder binder){
        binder.addValidators(categoryInsertValidator);
    }

    @InitBinder({"categoryUpdateDTO"})
    void initBinderUpdateDto(WebDataBinder binder){
        binder.addValidators(categoryUpdateValidator);
    }

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
        Category category = categoryInsertDTO.toEntity();
        categoryRepository.save(category);

        return "redirect:/admin/categories";
    }

    @Transactional
    @PostMapping("/admin/categories/{categoryCode}")
    public String update(@PathVariable String categoryCode, @Valid CategoryUpdateDTO categoryUpdateDTO, BindingResult result, Model model) {
        Optional<Category> possibleCategory = categoryRepository.findByCode(categoryCode);

        if (possibleCategory.isEmpty()) {
            return "errors/notFound";
        }

        if (result.hasErrors()) {
            model.addAttribute("categoryUpdateDTO", result.hasErrors() ? categoryUpdateDTO : new CategoryUpdateDTO(possibleCategory.get()));
            return "category/formUpdateCategory";
        }

        possibleCategory.get().update(categoryUpdateDTO);

        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/{code}")
    public String showFormCategory(@PathVariable String code, CategoryUpdateDTO categoryUpdateDTO, BindingResult result, Model model) {
        Optional<Category> possibleCategory = categoryRepository.findByCode(code);
        if (possibleCategory.isEmpty()) {
            return "errors/notFound";
        }
        model.addAttribute("categoryUpdateDTO", new CategoryUpdateDTO(possibleCategory.get()));

        return "category/formUpdateCategory";
    }

    @PostMapping("admin/categories/disable/{categoryCode}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public void disableCategory(@PathVariable String categoryCode){
        Optional<Category> possibleCategory = categoryRepository.findByCode(categoryCode);
        possibleCategory.get().disable();
    }
}
