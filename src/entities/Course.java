package entities;

import java.util.Date;
import java.util.List;

public class Course{
    private  Long id;
    private  String name;
    private String code;
    private Date estimatedTime;
    private boolean visibility;
    private String target;
    private String instructor;
    private String courseProgram;
    private String skillsDeveloped;
  


    public Course() {

    }

    public Course(Long id, String name, String code, Date estimatedTime, boolean visibility, String target, String instructor, String courseProgram, String skillsDeveloped) {
        this.id = id;
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Date estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getCourseProgram() {
        return courseProgram;
    }

    public void setCourseProgram(String courseProgram) {
        this.courseProgram = courseProgram;
    }

    public String getSkillsDeveloped() {
        return skillsDeveloped;
    }

    public void setSkillsDeveloped(String skillsDeveloped) {
        this.skillsDeveloped = skillsDeveloped;
    }
}
