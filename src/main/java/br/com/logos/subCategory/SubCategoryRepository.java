package br.com.logos.subCategory;

import br.com.logos.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
}
