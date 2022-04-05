package br.com.logos.subCategory;

public class SubCategoryDTO {
    private String name;
    private String code;
    private String studyGuide;

    public SubCategoryDTO(String name, String code, String studyGuide) {
        this.name = name;
        this.code = code;
        this.studyGuide = studyGuide;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getStudyGuide() {
        return studyGuide;
    }
}
