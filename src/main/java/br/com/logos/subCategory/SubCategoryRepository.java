package br.com.logos.subCategory;

import br.com.logos.subCategory.enums.SubCategoryStatus;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    List<SubCategory> findAllByStatus(SubCategoryStatus status);
}
