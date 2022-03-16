package Logos.course;

import Logos.subCategory.SubCategory;

public class CourseDTO {
    private int id;
    private String name;
    private String code;
    private int estimatedTime;
    private boolean visibility;
    private String targetAudience;
    private String instructorName;
    private String courseProgramDescription;
    private String developedSkills;
    private SubCategory subCategory;

    public CourseDTO(int id, String name, String code, int estimatedTime, String instructorName, SubCategory subCategory) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.estimatedTime = estimatedTime;
        this.instructorName = instructorName;
        this.subCategory = subCategory;
    }

    public CourseDTO(String name, String code, int estimatedTime, String instructorName) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.estimatedTime = estimatedTime;
        this.instructorName = instructorName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public Object getSubCategoryName() {
        return subCategory.getName();
    }

    public Object getSubcategoryId() {
        return subCategory.getId();
    }

    public boolean isVisibility() {
        return visibility;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public String getCourseProgramDescription() {
        return courseProgramDescription;
    }

    public String getDevelopedSkills() {
        return developedSkills;
    }
}
