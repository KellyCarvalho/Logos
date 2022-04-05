package br.com.logos.course;

public class CourseDTO {
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
}
