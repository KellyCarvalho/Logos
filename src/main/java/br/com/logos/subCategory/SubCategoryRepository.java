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
            SELECT s.name, s.status, s.identifier_code as code 
            FROM Subcategory as s 
            INNER JOIN Category as c ON c.id = s.category_id 
            where c.identifier_code = :categoryCode order by s.position
""", nativeQuery = true)
    List<SubCategoryProjection> getAllByCategoryOrderByOrder(@Param("categoryCode") String categoryCode);

    List<SubCategory> findAllByOrderByName();

    @Query(value = """
            select distinct s from SubCategory s
            join s.courses c
            where s.status = 'ACTIVE'
            and c.visibility = true
            and s.category.code = :code
            order by s.order          
            """)
    List<ActiveSubCategoriesWithCoursesProjection> getActiveSubCategoriesWithCourses(@Param("code") String categoryCode);
}
