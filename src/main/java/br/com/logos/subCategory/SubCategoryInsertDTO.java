package br.com.logos.subCategory;

import br.com.logos.category.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

public class SubCategoryInsertDTO {
    @NotBlank(message = "Nome é requerido")
    private String name;
    @Pattern(regexp = "[[a-z-]+]+", message = "Código  inválido, não pode ter caracteres especiais ou números, apenas o hífem é permitido, letras devem ser minúsculas")
    @NotBlank(message = "Codigo é requerido")
    private String code;
    private String description;
    private String studyGuide;
    private boolean status;
    @PositiveOrZero(message = "Ordem deve ter valor positivo ou 0")
    private int order;
    @NotNull(message = "Categoria não pode ser vazia")
    private Category  category;

    public boolean isStatus() {
        return status;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public void setStudyGuide(String studyGuide) {
        this.studyGuide = studyGuide;
    }

    public boolean isActive() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SubCategory toEntity(){
        return new SubCategory(this);
    }
}
