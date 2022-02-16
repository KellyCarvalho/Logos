package avalon.subCategory;

import avalon.category.Category;
import avalon.subCategory.enums.SubCategoryStatus;
import static avalon.subCategory.validation.SubCategoryValidation.*;
import static commonValidation.ObjectValidation.*;
import static commonValidation.StringValidation.*;

public class SubCategory {

    private String name;
    private String code;
    private String description;
    private String studyGuide;
    private SubCategoryStatus status=SubCategoryStatus.DISABLED;
    private int order;
    private Category category;

    public SubCategory(String name, String code, Category category) {
        isBlankEmptyOrNull(name, "Nome da SubCategoria é requerido, não pode ser vazia ou nula");
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


    public SubCategoryStatus getStatus() {
        return status;
    }

    public int getOrder() {
        return order;
    }

    public Category getCategory() {
        return category;
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
