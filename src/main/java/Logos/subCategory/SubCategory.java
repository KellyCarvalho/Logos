package Logos.subCategory;

import Logos.category.Category;
import Logos.subCategory.enums.SubCategoryStatus;

import java.util.Objects;

import static Logos.commonValidator.ObjectValidator.isObjectValid;
import static Logos.commonValidator.StringValidator.isNotBlankEmptyOrNull;
import static Logos.commonValidator.StringValidator.doesCodeContainsOnlyLettersInLowerCaseAndHyphen;
import static Logos.subCategory.enums.SubCategoryStatus.DISABLED;
import static Logos.subCategory.enums.SubCategoryStatus.ACTIVE;

public class SubCategory {

    private String name;
    private String code;
    private String description;
    private String studyGuide;
    private SubCategoryStatus status = DISABLED;
    private int order;
    private Category category;

    public SubCategory(String name, String code, Category category) {
        isNotBlankEmptyOrNull(name, "Nome da SubCategoria é requerido, não pode ser vazio ou nulo");
        doesCodeContainsOnlyLettersInLowerCaseAndHyphen(code, "Código da SubCategoria não é válido ou está null ou vazio - deve ter caracteres de a-z -" +
                "Único caractere especial permitido é o hífen");
        isObjectValid(category, "Categoria é obrigatória, não pode ser nula");
        this.name = name;
        this.code = code;
        this.category = category;
    }

    public SubCategory(String name, String code, String description, SubCategoryStatus status, int order, Category category) {
        this(name, code, category);
        this.description = description;
        this.status = status;
        this.order = order;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public int getOrder() {
        return order;
    }

    public SubCategoryStatus getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return ACTIVE.equals(this.status);
    }

    public boolean isEmptyDescription() {
        return this.getDescription() == null? false : this.getDescription().isEmpty();
    }

    public String getCategoryCode() {
        return this.getCategory().getCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubCategory that = (SubCategory) o;
        return code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return '\n' + "Nome=" + name + '\n' +
                "Código='" + code + '\n' +
                "Descrição='" + description + '\n' +
                "Guia de Estudos='" + studyGuide + '\n' +
                "Status=" + status + '\n' +
                "Order=" + order + '\n' + '\n' +
                "Categoria: " + '\n' + category.toString();
    }
}
