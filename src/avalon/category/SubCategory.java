package avalon.category;

public class SubCategory extends Category {

private int id;
private String categoryName;

    public SubCategory(String name, String code, String categoryName) {
        super(name, code);
        this.categoryName = categoryName;
    }

    public SubCategory(String name, String code, String description, String studyGuide, boolean active, int order, String categoryName) {
        super(name, code, description, studyGuide, active, order);
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
