package br.com.logos.course;

public class CourseListDTO {

    private String name;
    private String code;
    private boolean visibility;

    @Deprecated
    public CourseListDTO() {
    }

    public CourseListDTO(String name, String code, boolean visibility) {
        this.name = name;
        this.code = code;
        this.visibility = visibility;
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

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }
}
