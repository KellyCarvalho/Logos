package br.com.logos.subCategory;

import br.com.logos.subCategory.enums.SubCategoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    List<SubCategory> findAllByStatus(SubCategoryStatus status);

    Optional<SubCategory> findByCode(String code);

    @Query(value = "SELECT s.name,s.status, s.identifier_code as code FROM Subcategory as s  INNER JOIN Category  as c ON c.id = s.fk_category where c.identifier_code = :categoryCode order by s.position", nativeQuery = true)
    List<SubCategoryProjection> findAllByCategoryOrderByOrder(@Param("categoryCode") String categoryCode);
}
