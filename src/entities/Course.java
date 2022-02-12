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


    public Course(String name, String code,  int estimatedTime,String instructor) {
        fieldsContainsValue(name,code,instructor);
        isValidCode(code);
        isValidEstimatedTime(estimatedTime);
        this.name = name;
        this.code = code;
        this.instructor = instructor;

    }

    public Course(String name, String code, int estimatedTime, boolean visibility, String target, String instructor, String courseProgram, String skillsDeveloped) {
        isValidCode(code);
        fieldsContainsValue(name,code,instructor);
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

    public Long getId() {
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

    public boolean isVisibility() {
        return visibility;
    }

    public String getTarget() {
        return target;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getCourseProgram() {
        return courseProgram;
    }

    public String getSkillsDeveloped() {
        return skillsDeveloped;
    }
}
