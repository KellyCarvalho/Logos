package Logos.utils.builder;

import Logos.category.Category;
import Logos.subCategory.SubCategory;
import Logos.subCategory.enums.SubCategoryStatus;

public class SubCategoryBuilder {
    public String name;
    public String code;
    public Category category;
    private String description;
    private String studyGuide;
    private SubCategoryStatus status;
    private int order;

    public SubCategoryBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public SubCategoryBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public SubCategoryBuilder withCategory(Category category) {
        this.category = category;
        return this;
    }

    public SubCategoryBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public SubCategoryBuilder withStudyGuide(String studyGuide) {
        this.studyGuide = studyGuide;
        return this;
    }

    public SubCategoryBuilder withStatus(SubCategoryStatus status){
        this.status = status;
        return this;
    }

    public SubCategoryBuilder withOrder(int order){
        this.order = order;
        return this;
    }

    public SubCategory create() {
        return new SubCategory(name, code, description,status,order,category);
    }

}
