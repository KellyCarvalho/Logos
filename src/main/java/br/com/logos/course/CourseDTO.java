package br.com.logos.course;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CourseDTO {
    private final String name;
    private final String code;
    private final int estimatedTime;
    private final String developedSkills;

    public CourseDTO(Course course){
        this.code = course.getCode();
        this.name = course.getName();
        this.developedSkills = course.getDevelopedSkills();
        this.estimatedTime = course.getEstimatedTime();
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

    public String getDevelopedSkills() {
        return developedSkills;
    }

    public static List<CourseDTO> getPublicCoursesByCategory(List<Course> courses, Long categoryId) {
        List<CourseDTO> coursesDTOs = new ArrayList<>();
        courses.forEach(course -> {
            if (Objects.equals(course.getCategoryId(), categoryId)) {
                coursesDTOs.add(new CourseDTO(course));
            }
        });
        return coursesDTOs;
    }
}
