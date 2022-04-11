package br.com.logos.subCategory;

import org.springframework.data.jpa.domain.Specification;


public class SpecificationSubCategory {

    public static Specification<SubCategory> categoryCode( String categoryName){
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("fk_category"),categoryName));
    }
}
