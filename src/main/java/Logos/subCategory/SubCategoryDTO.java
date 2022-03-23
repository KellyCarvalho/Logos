package Logos.subCategory;

import Logos.category.CategoryDTO;
import Logos.subCategory.enums.SubCategoryStatus;

import javax.persistence.*;

import static Logos.subCategory.enums.SubCategoryStatus.DISABLED;

public class SubCategoryDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private String description;
    private String studyGuide;
    private SubCategoryStatus status = DISABLED;
    private int order;
    private CategoryDTO category;

    public SubCategoryDTO(Long id, String name, String code, CategoryDTO category) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.category = category;
    }

    public SubCategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public Long getCategoryId() {
        return this.category.getId();
    }

    public String getCategoryCode() {
        return this.category.getCode();
    }

    public String getCategoryName() {
        return this.category.getName();
    }

    public String getDescription() {
        return description;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public SubCategoryStatus getStatus() {
        return status;
    }

    public int getOrder() {
        return order;
    }
}
