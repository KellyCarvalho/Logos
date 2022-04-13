package br.com.logos.subCategory;

import br.com.logos.category.Category;
import br.com.logos.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.*;

@Controller
public class SubCategoryController {

    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/admin/subcategories/{categoryCode}")
    public String getSubCategoriesByCategoryCode(@PathVariable String categoryCode, Model model) {
        Optional<Category> possibleCategory = categoryRepository.findByCode(categoryCode);
        List<SubCategoryProjection> subcategories = subCategoryRepository.getAllByCategoryOrderByOrder(categoryCode);
        if (possibleCategory.isEmpty()) {
            return "errors/notFound";
        }
        model.addAttribute("subcategories", subcategories);
        model.addAttribute("category", possibleCategory.get());
        return "subcategory/subcategoriesList";
    }

    @Transactional
    @PostMapping("/admin/subcategories/new")
    public String insert(@Valid SubCategoryInsertDTO subCategoryInsertDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return showFormInsert(subCategoryInsertDTO, result, model);
        }
        SubCategory subCategory = subCategoryInsertDTO.toEntity();
        subCategoryRepository.save(subCategory);

        return "redirect:/admin/subcategories/" + subCategory.getCategoryCode();
    }

    @GetMapping("/admin/subcategories/new")
    public String showFormInsert(SubCategoryInsertDTO subCategoryInsertDTO, BindingResult result, Model model) {
        List<Category> categories = categoryRepository.findAllByOrderByName();
        model.addAttribute("categories", categories);
        return "subcategory/formInsertSubCategory";
    }

    @GetMapping("/admin/subcategories/{categoryCode}/{subcategoryCode}")
    public String showFormUpdate(@PathVariable String subcategoryCode, @PathVariable String categoryCode,
                                 SubCategoryUpdateDTO subCategoryUpdateDTO, BindingResult result, Model model) {
        Optional<Category> possibleCategory = categoryRepository.findByCode(categoryCode);
        Optional<SubCategory> possibleSubCategory = subCategoryRepository.findByCode(subcategoryCode);
        List<Category> categories = categoryRepository.findAllByOrderByName();
        if (possibleSubCategory.isEmpty() || possibleCategory.isEmpty()) {
            return "errors/notFound";
        }

        model.addAttribute("subCategoryUpdateDTO", result.hasErrors() ? subCategoryUpdateDTO : new SubCategoryUpdateDTO(possibleSubCategory.get()));
        model.addAttribute("categories", categories);
        return "/subcategory/formUpdateSubCategory";
    }

    @Transactional
    @PostMapping("/admin/subcategories/{categoryCode}/{subcategoryCode}")
    public String update(@PathVariable String categoryCode, @PathVariable String subcategoryCode,
                         @Valid SubCategoryUpdateDTO subCategoryUpdateDTO, BindingResult result, Model model) {
        Optional<Category> category = categoryRepository.findByCode(categoryCode);
        Optional<SubCategory> subCategory = subCategoryRepository.findByCode(subcategoryCode);

        if (subCategory.isEmpty() || category.isEmpty()) {
            return "errors/notFound";
        }

        if(result.hasErrors()){
            return showFormUpdate(subcategoryCode, categoryCode, subCategoryUpdateDTO, result, model);
        }

        subCategory.get().update(subCategoryUpdateDTO);

        return  "redirect:/admin/subcategories/"+subCategory.get().getCategoryCode();
    }

    @PostMapping("admin/subcategories/disable/{subcategoryCode}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public void disableSubCategory(@PathVariable String subcategoryCode){
        Optional<SubCategory> subCategory = subCategoryRepository.findByCode(subcategoryCode);
        subCategory.get().disable();
    }
}
