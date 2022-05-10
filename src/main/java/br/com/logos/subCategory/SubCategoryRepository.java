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

    @Query(value = """
            SELECT s.name, s.status, s.identifier_code AS code 
            FROM SubCategory AS s 
            INNER JOIN Category AS c ON c.id = s.category_id 
            WHERE c.identifier_code = :categoryCode 
            ORDER BY s.position
""", nativeQuery = true)
    List<SubCategoryProjection> getAllByCategoryOrderByOrder(@Param("categoryCode") String categoryCode);

    List<SubCategory> findAllByOrderByName();

    @Query(value = """
            SELECT distinct s
            FROM SubCategory s
            JOIN s.courses c
            WHERE s.status = 'ACTIVE'
            AND s.category.code = :code
            ORDER BY s.order
            """)
    List<ActiveSubCategoriesWithCoursesProjection> getActiveSubCategoriesWithCourses(@Param("code") String categoryCode);

    boolean existsByCodeAndIdNot(String code, Long id);
}
