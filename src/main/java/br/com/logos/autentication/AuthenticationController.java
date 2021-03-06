package br.com.logos.autentication;

import br.com.logos.category.ActiveCategoryWithActiveSubCategoriesProjection;
import br.com.logos.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AuthenticationController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/login")
    public String loginForm(Model model) {
        List<ActiveCategoryWithActiveSubCategoriesProjection> activeCategoryWithActiveSubCategoriesProjectionList = categoryRepository.getActiveCategoriesWithActiveSubCategories();
        model.addAttribute("categories", activeCategoryWithActiveSubCategoriesProjectionList);
        return "login/login";
    }
}
