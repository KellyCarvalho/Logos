package br.com.logos.category;

import br.com.logos.category.enums.CategoryStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static br.com.logos.category.enums.CategoryStatus.ACTIVE;
import static br.com.logos.category.enums.CategoryStatus.DISABLED;

public class CategoryUpdateDTO {

    private Long id;
    @NotBlank(message = "Nome não pode estar em branco")
    private String name;
    @NotBlank(message = "Código não pode estar em branco")
    private String code;
    private int order;
    private String studyGuide;
    private String description;
//    private CategoryStatus status;
    private String imageUrl;
    private String colorCode;

    @Deprecated
    public CategoryUpdateDTO(){
    }

    public CategoryUpdateDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.code = category.getCode();
        this.order = category.getOrder();
        this.studyGuide = category.getStudyGuide();
        this.description = category.getDescription();
//        this.status = category.getStatus() == null ? DISABLED : ACTIVE;
        this.imageUrl = category.getImageUrl();
        this.colorCode = category.getColorCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

//    public CategoryStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(CategoryStatus status) {
//        this.status = status;
//    }

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

    public void update(Category category) {
        category.setName(this.name);
        category.setCode(this.code);
        category.setDescription(this.description);
        category.setStudyGuide(this.studyGuide);
        category.setOrder(this.order);
//        category.setStatus(this.status);
        category.setImageUrl(this.imageUrl);
        category.setColorCode(this.colorCode);
    }

    @Override
    public String toString() {
        return "CategoryUpdateDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", order=" + order +
                ", studyGuide='" + studyGuide + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", colorCode='" + colorCode + '\'' +
                '}';
    }
}
