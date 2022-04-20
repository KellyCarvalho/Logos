package br.com.logos.category;

import br.com.logos.subCategory.ActiveSubCategoriesWithCoursesProjection;
import br.com.logos.subCategory.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class PublicPageCategoryController {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @GetMapping("category/{categoryCode}")
    public String categoryPublicPage(@PathVariable String categoryCode, Model model) {
        Optional<CategoryNameAndCodeProjection> possibleCategory = categoryRepository.getCategoryNameAndCodeByCode(categoryCode);
        if (possibleCategory.isEmpty()){
            return "errors/notFound";
        }
        List<ActiveSubCategoriesWithCoursesProjection>
                activeSubCategoriesWithCourses = subCategoryRepository.getActiveSubCategoriesWithCourses(categoryCode);

        model.addAttribute("category", possibleCategory.get());
        model.addAttribute("subCategories", activeSubCategoriesWithCourses);
        return "/publicPageCategory/publicPageCategoryList";
    }
}
