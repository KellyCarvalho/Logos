package Logos.subCategory;

import Logos.category.CategoryDTO;

public class SubCategoryDTO {

    private final int id;
    private final String name;
    private String code;
    private CategoryDTO category;

    public SubCategoryDTO(int id, String name, String code, CategoryDTO category) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.category = category;
    }

    public SubCategoryDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public int getCategoryId() {
        return this.category.getId();
    }

    public String getCategoryCode() {
        return this.category.getCode();
    }

    public String getCategoryName() {
        return this.category.getName();
    }
}
