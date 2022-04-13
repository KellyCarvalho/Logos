package br.com.logos.category;

import br.com.logos.category.enums.CategoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    List<Category> findCategoriesByStatusOrderByOrder(CategoryStatus status);

    Optional<Category> findByCode(String code);

    List<Category> findByOrderByOrder();

    List<Category> findAllByOrderByName();

    @Query(value = "select id, name from Category where identifier_code = :categoryCode order by position ", nativeQuery = true)
    Optional<CategoryProjection> findOrderByOrder(@Param("categoryCode") String categoryCode);

}
