package br.com.logos.subCategory;

import br.com.logos.category.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.List;

public class SubCategoryUpdateDTO {

    private Long id;
    @NotBlank(message = "Nome é requerido")
    private String name;
    @Pattern(regexp = "[[a-z-]+]+", message = "Código  inválido, não pode ter caracteres especiais ou números, apenas o hífem é permitido, letras devem ser minúsculas")
    @NotBlank(message = "Codigo é requerido")
    private String code;
    private String description;
    private String studyGuide;
    private boolean active;
    @PositiveOrZero(message = "Ordem deve ter valor positivo ou 0")
    private int order;
    @NotNull(message = "Categoria não pode ser vazia")
    private Category category;

    @Deprecated
    public SubCategoryUpdateDTO() {
    }

    public SubCategoryUpdateDTO(SubCategory subCategory) {
        this.id = subCategory.getId();
        this.name = subCategory.getName();
        this.code = subCategory.getCode();
        this.description = subCategory.getDescription();
        this.order = subCategory.getOrder();
        this.studyGuide = subCategory.getStudyGuide();
        this.active = subCategory.isActive();
        this.category = subCategory.getCategory();
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
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
}
