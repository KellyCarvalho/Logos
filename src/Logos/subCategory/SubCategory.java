package Logos.subCategory;

import Logos.category.Category;
import Logos.subCategory.enums.SubCategoryStatus;

import static Logos.subCategory.validation.SubCategoryValidation.isValidOrder;
import static commonValidation.ObjectValidation.isObjectValid;
import static commonValidation.StringValidation.isNotBlankEmptyOrNull;
import static commonValidation.StringValidation.isValidCode;

public class SubCategory {

    private String name;
    private String code;
    private String description;
    private String studyGuide;
    private SubCategoryStatus status = SubCategoryStatus.DISABLED;
    private int order;
    private Category category;

    public SubCategory(String name, String code, Category category) {
        isNotBlankEmptyOrNull(name, "Nome da SubCategoria é requerido, não pode ser vazia ou nula");
        isValidCode(code, "Código da SubCategoria não é válido ou está null ou vazio - deve ter caracteres de a-z - algarismos de 0-9 - Único caractere especial permitido é o hífen");
        isObjectValid(category, "Categoria é obrigatória, não pode ser nula");
        this.name = name;
        this.code = code;
        this.category = category;
    }

    public SubCategory(String name, String code, String description, String studyGuide, SubCategoryStatus status, int order, Category category) {
        this(name, code, category);
        isValidOrder(order);
        this.description = description;
        this.studyGuide = studyGuide;
        this.status = status;
        this.order = order;
    }

    public String getCode() {
        return code;
    }

    public int getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "SubCategory" + '\n' +
                "Nome='" + name + '\n' +
                "Código='" + code + '\n' +
                "Descrição='" + description + '\n' +
                "Guia de Estudos='" + studyGuide + '\n' +
                "Status=" + status + '\n' +
                "Order=" + order + '\n' + '\n' +
                "Categoria: " + '\n' + category.toString();
    }
}
