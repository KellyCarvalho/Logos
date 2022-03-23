package Logos.utils.builder;

import Logos.category.Category;
import Logos.category.enums.CategoryStatus;

public class CategoryBuilder {

    public String name;
    public String code;
    private int order;
    private String description;
    private String studyGuide;
    private CategoryStatus status;
    private String imageUrl;
    private String colorCode;


    public CategoryBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CategoryBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public CategoryBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public CategoryBuilder withStudyGuide(String studyGuide) {
        this.studyGuide = studyGuide;
        return this;
    }

    public CategoryBuilder withStatus(CategoryStatus status){
        this.status = status;
        return this;
    }

    public CategoryBuilder withOrder(int order){
        this.order = order;
        return this;
    }

    public CategoryBuilder withImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
        return this;
    }

    public CategoryBuilder withColorCode(String colorCode){
        this.colorCode = colorCode;
        return this;
    }

    public Category create(){
        return new Category(name,code,description,status,order,imageUrl,colorCode);
    }
}
