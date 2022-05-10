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
            SELECT c.identifier_code `code`, c.name, c.image_url `imageUrl` 
            FROM Category c 
            WHERE c.identifier_code = :code
            """, nativeQuery = true)
    Optional<CategoryProjection> getCategoryByCode(@Param("code") String code);

    List<Category> findByOrderByOrder();

    List<Category> findAllByOrderByName();

    @Query(value = """
            SELECT DISTINCT c FROM Category c 
            JOIN c.subCategories s 
            JOIN s.courses co
            WHERE c.status = 'ACTIVE'
            AND s.status = 'ACTIVE'
            AND co.visibility = true
            ORDER BY c.order
            """)
    List<ActiveCategoryWithActiveSubCategoriesProjection> getActiveCategoriesWithActiveSubCategories();

    boolean existsByCodeAndIdNot(String code, Long id);
}
