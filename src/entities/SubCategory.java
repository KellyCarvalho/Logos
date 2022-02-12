package entities;

public class SubCategory extends Category {


    public SubCategory(String name, String code, String description, String studyGuide, boolean active, int order, String codeColor) {
        super(name, code, description, studyGuide, active, order, codeColor);
    }

    public SubCategory(String name, String code) {
        super(name, code);
    }


}
