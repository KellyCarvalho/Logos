package br.com.logos.category;

import br.com.logos.category.enums.CategoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findCategoriesByStatusOrderByOrder(CategoryStatus status);

    Optional<Category> findByCode(String code);

    @Query(value = """
            select c.identifier_code `code`, c.name, c.image_url `imageUrl` 
            from Category c 
            where c.identifier_code = :code
            """, nativeQuery = true)
    Optional<CategoryProjection> getCategoryByCode(@Param("code") String code);

    List<Category> findByOrderByOrder();

    List<Category> findAllByOrderByName();

    @Query(value = """
            select distinct c from Category c 
            join c.subCategories s 
            join s.courses co 
            where c.status = 'ACTIVE'
            """)
    List<CategoryActiveWithSubCategoriesNameProjection> getActiveCategoriesWithSubCategories();
}
