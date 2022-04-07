package br.com.logos.course;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CourseDTO {
    //TODO podem ser final
    private String name;
    private String code;
    private int estimatedTime;
    private String developedSkills;

    public CourseDTO(String name, String code, int estimatedTime, String developedSkills) {
        this.name = name;
        this.code = code;
        this.estimatedTime = estimatedTime;
        this.developedSkills = developedSkills;
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
            if (course.getCategoryId() == categoryId) {
                coursesDTOs.add(new CourseDTO(course.getName(), course.getCode(),
                        course.getEstimatedTime(), course.getDevelopedSkills()));
            }
        });
//        courses.forEach(course -> {
//            if (course.getCategoryId() == categoryId) {
//                //TODO passar course
//                coursesDTOs.add();
//            }
//        });
        return coursesDTOs;
    }
}
