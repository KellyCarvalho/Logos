package Logos.category;


import Logos.category.enums.CategoryStatus;

import java.util.Objects;

import static Logos.category.enums.CategoryStatus.DISABLED;
import static Logos.category.enums.CategoryStatus.ACTIVE;

import static Logos.commonValidator.StringValidator.*;

public class Category {

    private int id;
    private String name;
    private String code;
    private String description;
    private String studyGuide;
    private CategoryStatus status = DISABLED;
    private int order;
    private String imageUrl;
    private String colorCode;

    public Category(String name, String code) {
        isNotBlankEmptyOrNull(name, "Nome da categoria é requerido, não pode ser vazio ou em branco");
        doesCodeContainsOnlyLettersInLowerCaseAndHyphen(code, "Código da Categoria não é válido ou está null ou vazio - deve ter caracteres de a-z - " +
                "Único caractere especial permitido é o hífen");
        this.name = name;
        this.code = code;
    }

    public Category(String name, String code, CategoryStatus status) {
        this(name, code);
        this.status = status;
    }

    public Category(String name, String code, String description, CategoryStatus status, int order, String imageUrl, String colorCode) {
        this(name, code);
        isValidColor(colorCode, "Cor de categoria não é válida");
        this.description = description;
        this.status = status;
        this.order = order;
        this.imageUrl = imageUrl;
        this.colorCode = colorCode;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getOrder() {
        return order;
    }

    public String getColorCode() {
        return colorCode;
    }

    public CategoryStatus getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return ACTIVE.equals(this.getStatus());
    }

    @Override
    public String toString() {
        return "Nome= " + name + '\n' +
                "Código= " + code + '\n' +
                "Descrição= " + description + '\n' +
                "Guia de estudo=" + studyGuide + '\n' +
                "status= " + status + '\n' +
                "Ordem= " + order + '\n' +
                "imageUrl= " + imageUrl + '\n' +
                "Cor em Hexadecimal= " + colorCode + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return code.equals(category.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
