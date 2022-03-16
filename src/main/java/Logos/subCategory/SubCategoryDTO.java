package Logos.subCategory;

import Logos.category.CategoryDTO;

public class SubCategoryDTO {

    private int id;
    private String name;
    private String code;
    private CategoryDTO category;

    public SubCategoryDTO(int id, String name, String code, CategoryDTO category) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.category = category;
    }

    public SubCategoryDTO() {
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

    public int getCategoryId() {
        return category.getId();
    }

    @Override
    public String toString() {
        return "SubCategoryDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", category=" + category +
                '}';
    }
}
