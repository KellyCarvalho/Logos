package br.com.logos.category;

import br.com.logos.category.enums.CategoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    List<Category> findCategoriesByStatusOrderByOrder(CategoryStatus status);
}
