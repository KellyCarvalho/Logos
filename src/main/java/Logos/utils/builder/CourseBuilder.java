package Logos.utils.builder;

import Logos.course.Course;
import Logos.subCategory.SubCategory;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class CourseBuilder {

    private String name;
    private String code;
    private int estimatedTime;
    private boolean visibility;
    private String targetAudience;
    private String instructorName;
    private String courseProgramDescription;
    private String developedSkills;
    private SubCategory subCategory;

    public CourseBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CourseBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public CourseBuilder withEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
        return this;
    }

    public CourseBuilder withInstructorName(String instructorName) {
        this.instructorName = instructorName;
        return this;
    }

    public CourseBuilder withSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
        return this;
    }

    public CourseBuilder withVisibility(boolean visibility) {
        this.visibility = visibility;
        return this;
    }

    public CourseBuilder withTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
        return this;
    }

    public CourseBuilder withCourseProgramDescription(String courseProgramDescription) {
        this.courseProgramDescription = courseProgramDescription;
        return this;
    }

    public CourseBuilder withDevelopedSkills(String developedSkills) {
        this.developedSkills = developedSkills;
        return this;
    }

    public Course create() {
        return new Course(name, code, estimatedTime, visibility, targetAudience, instructorName, courseProgramDescription, developedSkills, subCategory);
    }
}
