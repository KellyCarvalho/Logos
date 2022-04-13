package br.com.logos.subCategory;

import br.com.logos.category.Category;
import br.com.logos.category.CategoryProjection;
import br.com.logos.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        List<SubCategoryProjection> subcategories = subCategoryRepository.findAllByCategoryOrderByOrder(categoryCode);
        Optional<Category> category = categoryRepository.findByCode(categoryCode);
        model.addAttribute("subcategories", subcategories);
        category.ifPresent(value -> model.addAttribute("category", value));
        return "subcategory/subcategoriesList";
    }

    @Transactional
    @PostMapping("/admin/subcategories/new")
    public String insert(@Valid SubCategoryInsertDTO subCategoryInsertDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return showFormInsert(subCategoryInsertDTO, result, model);
        }
        SubCategory subCategory = subCategoryInsertDTO.toEntity(subCategoryInsertDTO);
        subCategoryRepository.save(subCategory);

        return "redirect:/admin/subcategories/" + subCategory.getCategory().getCode();
    }

    @GetMapping("/admin/subcategories/new")
    public String showFormInsert(SubCategoryInsertDTO subCategoryInsertDTO, BindingResult result, Model model) {
        List<Category> categories = categoryRepository.findAllByOrderByName();
        model.addAttribute("categories", categories);
        return "subcategory/formInsertSubCategory";
    }

    @GetMapping("/admin/subcategories/{categoryCode}/{subcategoryCode}")
    public String showSubCategory(@PathVariable String subcategoryCode, @PathVariable String categoryCode, SubCategoryUpdateDTO subCategoryUpdateDTO, BindingResult result, Model model) {
        Optional<SubCategory> subCategory = subCategoryRepository.findByCode(subcategoryCode);
        Optional<CategoryProjection> categoryProjection = categoryRepository.findOrderByOrder(categoryCode);
        List<Category> categories = categoryRepository.findAllByOrderByName();
        if (subCategory.isEmpty()) {
            return "erros/notFound";
        }
        model.addAttribute("categories", categories);
        model.addAttribute("subcategoryUpdateDTO", new SubCategoryUpdateDTO(subCategory.get()));
        model.addAttribute("categoryFromSubCategory", categoryProjection.get());
        return "/subcategory/formUpdateSubCategory";
    }

    @Transactional
    @PostMapping("/admin/subcategories/{categoryCode}/{subcategoryCode}")
    public String update(@PathVariable String categoryCode, @PathVariable String subcategoryCode, @Valid SubCategoryUpdateDTO subCategoryUpdateDTO, BindingResult result, Model model) {
        Optional<SubCategory> subCategory = subCategoryRepository.findByCode(subcategoryCode);

        if (subCategory.isEmpty()) {
        return "errors/notFound";
        }

        if(result.hasErrors()){
            model.addAttribute("categoryUpdateDTO", result.hasErrors() ? subCategoryUpdateDTO : new SubCategoryUpdateDTO(subCategory.get()));
            return "subcategory/formUpdateSubCategory";
        }

        subCategory.get().update(subCategoryUpdateDTO);

        return  "redirect:/admin/subcategories/"+subCategory.get().getCategory().getCode();
    }

    @PostMapping("admin/subcategories/disable/{subcategoryCode}")
    @Transactional
    @ResponseBody
    public void disableSubCategory(@PathVariable String subcategoryCode){
        Optional<SubCategory> subCategory = subCategoryRepository.findByCode(subcategoryCode);
        subCategory.get().disableCategory();
    }
}
