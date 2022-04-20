package br.com.logos.category;

import br.com.logos.subCategory.SubCategory;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface CategoryActiveWithSubCategoriesNameProjection {

    @Value("#{target.code}")
    String getCode();
    @Value("#{target.name}")
    String getName();
    @Value("#{target.imageUrl}")
    String getImageUrl();
    @Value("#{target.subCategories}")
    List<SubCategory> getSubCategories();
}
