package Logos.subCategory;

import Logos.category.Category;
import Logos.subCategory.enums.SubCategoryStatus;

import static Logos.commonValidator.ObjectValidator.isObjectValid;
import static Logos.commonValidator.StringValidator.isNotBlankEmptyOrNull;
import static Logos.commonValidator.StringValidator.isValidCode;
import static Logos.commonValidator.UtilsValidator.isValidOrder;

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
        isValidCode(code, "Código da SubCategoria não é válido ou está null ou vazio - deve ter caracteres de a-z - algarismos de 0-9 - Único caractere especial permitido é o hífen");
        isObjectValid(category, "Categoria é obrigatória, não pode ser nula");
        this.name = name;
        this.code = code;
        this.category = category;
    }

    public void setOrder(int order) {
        isValidOrder(order, "Ordem de subcategoria não pode ser menor que 1");
        this.order = order;
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
