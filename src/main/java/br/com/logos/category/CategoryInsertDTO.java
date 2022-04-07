package br.com.logos.category;

import br.com.logos.category.enums.CategoryStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static br.com.logos.category.enums.CategoryStatus.ACTIVE;
import static br.com.logos.category.enums.CategoryStatus.DISABLED;

public class CategoryInsertDTO {

    //TODO colocar o pattern de código e cor
    //TODO validar min de order
    //TODO validar a url
    @NotBlank(message = "Nome não pode estar em branco")
    private String name;
    @NotBlank(message = "Código não pode estar em branco")
    private String code;
    private int order;
    private String studyGuide;
    private String description;
    private CategoryStatus status = DISABLED;
    private String imageUrl;
    private String colorCode;

    public CategoryInsertDTO(String name, String code, int order, String studyGuide, String description, CategoryStatus status, String imageUrl, String colorCode) {
        this.name = name;
        this.code = code;
        this.order = order;
        this.studyGuide = studyGuide;
        this.description = description;
        this.status = status;
        this.imageUrl = imageUrl;
        this.colorCode = colorCode;
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

   public Category insertDTOtoEntity(CategoryInsertDTO categoryInsertDTO) {
        return new Category(categoryInsertDTO.getName(), categoryInsertDTO.getCode(), categoryInsertDTO.getDescription(),
                categoryInsertDTO.getStudyGuide(), categoryInsertDTO.getStatus(), categoryInsertDTO.getOrder(),
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
                ", status=" + status +
                ", imageUrl='" + imageUrl + '\'' +
                ", colorCode='" + colorCode + '\'' +
                '}';
    }
}
