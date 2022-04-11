package br.com.logos.subCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class SubCategoryController {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @GetMapping("/admin/subcategories/{categoryCode}")
    public String getSubCategoriesByCategoryCode(@PathVariable String categoryCode, Model model) {
        List<SubCategory> subCategories = subCategoryRepository.findAll();
//       List<SubCategoryListDTO> subCategoryListDTO = SubCategoryListDTO.toView(subCategories);
      //  model.addAttribute("subcategories", subCategoryListDTO);

        return subCategories.toString();
    }
}
