package Logos.subCategory;

import Logos.category.Category;
import Logos.subCategory.enums.SubCategoryStatus;

import java.util.List;

import static Logos.commonValidator.ObjectValidator.isObjectValid;
import static Logos.commonValidator.StringValidator.isNotBlankEmptyOrNull;
import static Logos.commonValidator.StringValidator.isValidCode;

public class SubCategory {

    private String name;
    private String code;
    private String description;
    private String studyGuide;
    private SubCategoryStatus status = SubCategoryStatus.DISABLED;
    private int order;
    private Category category;

    public SubCategory(String name, String code, Category category) {
        isNotBlankEmptyOrNull(name, "Nome da SubCategoria é requerido, não pode ser vazio ou nulo");
        isValidCode(code, "Código da SubCategoria não é válido ou está null ou vazio - deve ter caracteres de a-z -" +
                " algarismos de 0-9 - Único caractere especial permitido é o hífen");
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

    public String getCategoryDescription() {
        return this.getCategory().getDescription();
    }

    public static List<SubCategory> getCategoriesWithoutDescription(List<SubCategory> subCategories) {
        return subCategories.stream().filter(subCategory -> subCategory.getCategoryDescription().isBlank() ||
                subCategory.getCategoryDescription().isEmpty()).toList();
    }

    public static List<SubCategory> getSubCategoriesWithoutDescription(List<SubCategory> subCategories) {
        return subCategories.stream().filter(subCategory -> subCategory.getDescription().isBlank() ||
                subCategory.getDescription().isEmpty()).toList();
    }

    public static Long getQuantitySubCategoriesActivesWithDescription(List<SubCategory> subCategories) {
        return subCategories.stream().filter(subCategory -> subCategory.getStatus().equals(SubCategoryStatus.ACTIVE) &&
                !subCategory.getDescription().isBlank() || !subCategory.getDescription().isEmpty()).count();
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
