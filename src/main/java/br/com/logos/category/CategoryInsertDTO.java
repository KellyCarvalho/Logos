package br.com.logos.category;

import br.com.logos.category.enums.CategoryStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

public class CategoryInsertDTO {

    @NotBlank(message = "Nome não pode estar em branco")
    private String name;
    @NotBlank(message = "Código não pode estar em branco")
    @Pattern(regexp = "[a-z-]+", message = "Código  inválido, não pode ter caracteres especiais ou números, apenas o hífem é perminido, letras devem ser minúsculas")
    private String code;
    @PositiveOrZero(message = "Ordem deve ter valor positivo ou 0")
    private int order;
    private String studyGuide;
    private String description;
    private boolean active;
    private String imageUrl;
    @Pattern(regexp = "^#([a-fA-F0-9]){6}?$|^[\s]*$", message = "cor inválida")
    private String colorCode;

    @Deprecated
    public CategoryInsertDTO(){}

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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public void setStudyGuide(String studyGuide) {
        this.studyGuide = studyGuide;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public Category toEntity(CategoryInsertDTO categoryInsertDTO) {
        return new Category(categoryInsertDTO.getName(), categoryInsertDTO.getCode(), categoryInsertDTO.getDescription(),
                categoryInsertDTO.getStudyGuide(), categoryInsertDTO.active ? CategoryStatus.ACTIVE : CategoryStatus.DISABLED, categoryInsertDTO.getOrder(),
                categoryInsertDTO.getImageUrl(), categoryInsertDTO.getColorCode());
    }

    @Override
    public String toString() {
        return "CategoryInsertDTO{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", order=" + order +
                ", studyGuide='" + studyGuide + '\'' +
                ", description='" + description + '\'' +
                ", status=" + active +
                ", imageUrl='" + imageUrl + '\'' +
                ", colorCode='" + colorCode + '\'' +
                '}';
    }
}
