package avalon.category;

import avalon.category.Category;

import static avalon.category.validation.SubCategoryValidation.fieldsContainsValue;
import static avalon.category.validation.SubCategoryValidation.isValidCode;

public class SubCategory extends Category {

private int id;
private String categoryName;

    public SubCategory(String name, String code, String categoryName) {
        super(name, code);
        isValidCode(code);
        fieldsContainsValue(name,code,categoryName);
        this.categoryName = categoryName;
    }

    public SubCategory(String name, String code, String description, String studyGuide, boolean active, int order, String categoryName) {
        super(name, code, description, studyGuide, active, order);
        isValidCode(code);
        fieldsContainsValue(name,code,categoryName);
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
