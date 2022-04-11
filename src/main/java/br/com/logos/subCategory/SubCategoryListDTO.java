package br.com.logos.subCategory;

import br.com.logos.category.Category;
import br.com.logos.subCategory.enums.SubCategoryStatus;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static br.com.logos.subCategory.enums.SubCategoryStatus.DISABLED;

public class SubCategoryListDTO {
    private Long id;
    private String name;
    private String code;
    private String description;
    private String studyGuide;
    private SubCategoryStatus status = DISABLED;
    private int order;
    private String categoryName;

    @Deprecated
    public SubCategoryListDTO() {
    }

    public SubCategoryListDTO(SubCategory subCategory) {
        this.name = subCategory.getName();
        this.code = subCategory.getCode();
        this.status = subCategory.getStatus();
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStudyGuide(String studyGuide) {
        this.studyGuide = studyGuide;
    }

    public void setStatus(SubCategoryStatus status) {
        this.status = status;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public static List<SubCategoryListDTO> toView(List<SubCategory> subCategories){
        List<SubCategoryListDTO> subCategoryList = new ArrayList<>();
        subCategories.forEach(subCategory -> {
            subCategoryList.add(new SubCategoryListDTO(subCategory));
        });
        return  subCategoryList;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "SubCategoryListDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", studyGuide='" + studyGuide + '\'' +
                ", status=" + status +
                ", order=" + order +
                '}';
    }
}
