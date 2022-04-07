package br.com.logos.category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

import static br.com.logos.commonValidator.StringValidator.*;

public class CategoryUpdateDTO {

    private Long id;
    @NotBlank(message = "Nome não pode estar em branco")
    private String name;
    @NotBlank(message = "Código não pode estar em branco")
    @Pattern(regexp = "[[a-z-]+]+", message = "Código  inválido, não pode ter caracteres especiais ou números, apenas o hífem é perminido, letras devem ser minúsculas")
    private String code;
    @PositiveOrZero(message = "Ordem deve ter valor positivo ou 0, se nada for preenchido valor default será 0")
    private String order;
    private String studyGuide;
    private String description;
    private boolean active;
    private String imageUrl;
    @Pattern(regexp = "^#([a-fA-F0-9]){6}?$|^[\s]*$", message = "cor inválida")
    private String colorCode;

    @Deprecated
    public CategoryUpdateDTO(){
    }

    public CategoryUpdateDTO(Category category) {
        isValidColor(category.getColorCode(), "Cor não é válida");
        isValidCode(category.getCode(), "Código não é válido");
        isNotBlankEmptyOrNull(category.getName(), "Nome é requerido");
        this.id = category.getId();
        this.name = category.getName();
        this.code = category.getCode();
        this.order = Integer.toString(category.getOrder());
        this.studyGuide = category.getStudyGuide();
        this.description = category.getDescription();
        this.active = category.isActive();
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

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
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

    public int convertOrder(String order){
        return  order != null && !order.isBlank() && !order.isEmpty() ? Integer.parseInt(order)  : 0;
    }

    public void update(Category category) {
        category.setName(this.name);
        category.setCode(this.code);
        category.setDescription(this.description);
        category.setStudyGuide(this.studyGuide);
        category.setOrder(convertOrder(this.order));
        category.setStatus(this.active);
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
