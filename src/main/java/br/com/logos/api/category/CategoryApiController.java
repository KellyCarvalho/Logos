package br.com.logos.api.category;

import br.com.logos.category.CategoryListDTO;
import br.com.logos.category.CategoryRepository;
import br.com.logos.category.enums.CategoryStatus;
import br.com.logos.course.CourseRepository;
import br.com.logos.subCategory.SubCategoryRepository;
import br.com.logos.subCategory.enums.SubCategoryStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static br.com.logos.category.CategoryListDTO.toListCategoryDTO;

@RequiredArgsConstructor
@RestController
public class CategoryApiController {

    private final CategoryRepository categoryRepository;

    private final SubCategoryRepository subCategoryRepository;

    private final CourseRepository courseRepository;

    @Cacheable("categories")
    @GetMapping(value = "/api/categories", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<CategoryListDTO>> getActiveCategories() {
        return ResponseEntity.ok().body(toListCategoryDTO(
                categoryRepository
                        .findCategoriesByStatusOrderByOrder(CategoryStatus.ACTIVE),
                courseRepository
                        .findByVisibilityAndSubCategory_Category_StatusOrderBySubCategory_Category_Order(true, CategoryStatus.ACTIVE),
                subCategoryRepository
                        .findAllByStatus(SubCategoryStatus.ACTIVE)));
    }
}
