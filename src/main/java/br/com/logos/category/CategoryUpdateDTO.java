package br.com.logos.category;

import br.com.logos.category.enums.CategoryStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static br.com.logos.category.enums.CategoryStatus.DISABLED;

public class CategoryUpdateDTO {

    private Long id;
    @NotBlank(message = "Nome não pode estar em branco")
    @NotEmpty(message = "Nome não pode estar Vazio")
    @NotNull(message = "Nome não pode estar nulo")
    private String name;
    @NotBlank(message = "Código não pode estar em branco")
    @NotEmpty(message = "Código não pode estar Vazio")
    @NotNull(message = "Código não pode estar nulo")
    private String code;
    private int order;
    private String studyGuide;
    private String description;
    private CategoryStatus status = DISABLED;
    private String imageUrl;
    @Pattern(regexp = "^#([a-fA-F0-9]){3}(([a-fA-F0-9]){3})?$", message = "Deve ser uma cor válida")
    private String colorCode;

    public CategoryUpdateDTO(Long id, String name, String code, int order, String studyGuide, String description,
                             CategoryStatus status, String imageUrl, String colorCode) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.order = order;
        this.studyGuide = studyGuide;
        this.description = description;
        this.status = status;
        this.imageUrl = imageUrl;
        this.colorCode = colorCode;
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

    public int getOrder() {
        return order;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public String getDescription() {
        return description;
    }

    public CategoryStatus getStatus() {
        return status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setStatus(CategoryStatus status) {
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
