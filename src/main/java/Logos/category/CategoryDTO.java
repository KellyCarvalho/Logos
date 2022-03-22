package Logos.category;

public class CategoryDTO {

    private final Long id;
    private final String name;
    private final String code;

    public CategoryDTO(Long id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
