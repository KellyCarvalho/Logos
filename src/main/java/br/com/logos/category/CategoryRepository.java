package br.com.logos.category;

import br.com.logos.category.enums.CategoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    List<Category> findCategoriesByStatusOrderByOrder(CategoryStatus status);

    Optional<Category> findByCode(String code);

    List<Category> findByOrderByOrder();

    List<Category> findAllByOrderByName();

    @Query(value = """
          (select distinct c.name `name`, c.image_url `imageUrl`, GROUP_CONCAT(Subcategory.name  SEPARATOR ', ') `subCategoriesName`
            from Subcategory inner join Category c on Subcategory.category_id = c.id 
            where Subcategory.category_id = c.id group by Subcategory.category_id  )
        
        """, nativeQuery = true)
    List<CategoryActiveWithSubCategporiesNameProjection> getActiveCategoriesWithSubCategoriesNames();
}
