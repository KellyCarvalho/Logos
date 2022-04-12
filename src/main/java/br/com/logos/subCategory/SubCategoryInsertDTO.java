package br.com.logos.subCategory;

import br.com.logos.category.Category;
import br.com.logos.subCategory.enums.SubCategoryStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.List;

public class SubCategoryInsertDTO {
    @NotBlank(message = "Nome é requerido")
    private String name;
    @Pattern(regexp = "[[a-z-]+]+", message = "Código  inválido, não pode ter caracteres especiais ou números, apenas o hífem é perminido, letras devem ser minúsculas")
    @NotBlank(message = "Codigo é requerido")
    private String code;
    private String description;
    private String studyGuide;
    private boolean status;
    @PositiveOrZero(message = "Ordem deve ter valor positivo ou 0")
    private String order;
    @NotNull(message = "Categoria não pode ser vazia")
    private Category  category;
    List<Category> categories = new ArrayList<>();

    public boolean isStatus() {
        return status;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
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

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SubCategory toEntity(SubCategoryInsertDTO subCategoryInsertDTO){
        return new SubCategory(subCategoryInsertDTO);
    }

    public int convertOrder(String order) {
        return order != null && !order.isBlank() && !order.isEmpty() ? Integer.parseInt(order) : 0;
    }
}
