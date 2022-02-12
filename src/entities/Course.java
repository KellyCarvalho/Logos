package entities;

import static validation.CourseValidation.*;


public class Course {
    private  Long id;
    private  String name;
    private String code;
    private int estimatedTime;
    private boolean visibility;
    private String target;
    private String instructor;
    private String courseProgram;
    private String skillsDeveloped;
    private Section section;

    public Course(String name, String code, Section section) {
        fieldsContainsValue(name,code);
        this.name = name;
        this.code = code;
        this.section=section;
    }

    public Course(String name, String code, int estimatedTime, boolean visibility, String target, String instructor, String courseProgram, String skillsDeveloped) {
        isValidCode(code);
        fieldsContainsValue(name,code);
        isValidEstimatedTime(estimatedTime);
        this.name = name;
        this.code = code;
        this.estimatedTime = estimatedTime;
        this.visibility = visibility;
        this.target = target;
        this.instructor = instructor;
        this.courseProgram = courseProgram;
        this.skillsDeveloped = skillsDeveloped;
    }
}
