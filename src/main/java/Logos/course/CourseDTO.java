package Logos.course;

import Logos.subCategory.SubCategoryDTO;

public class CourseDTO {

    private final int id;
    private final String name;
    private final int estimatedTime;
    private final SubCategoryDTO subCategory;

    public CourseDTO(int id, String name, int estimatedTime, SubCategoryDTO subCategory) {
        this.id = id;
        this.name = name;
        this.estimatedTime = estimatedTime;
        this.subCategory = subCategory;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public SubCategoryDTO getSubCategory() {
        return subCategory;
    }

    public Object getSubCategoryName() {
        return subCategory.getName();
    }

    public Object getSubcategoryId() {
        return subCategory.getId();
    }
}