package Logos.category;

public class CategoryDTO {

    private final int id;
    private final String name;
    private final String code;

    public CategoryDTO(int id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
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
}
