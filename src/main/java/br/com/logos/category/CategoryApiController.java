package br.com.logos.category;

import br.com.logos.category.enums.CategoryStatus;
import br.com.logos.course.CourseRepository;
import br.com.logos.subCategory.SubCategoryRepository;
import br.com.logos.subCategory.enums.SubCategoryStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static br.com.logos.category.CategoryListDTO.toListCategoryDTO;

@Controller
public class CategoryApiController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Cacheable("categories")
    @GetMapping(value = "/api/categories", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
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

    @GetMapping("/bGltcGEtby1jYWNoZS1kYS1hcGktYWU")
    @CacheEvict(value = "categories", allEntries = true)
    @ResponseStatus(HttpStatus.OK)
    public void invalidCache(){
    }
}
