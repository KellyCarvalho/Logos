package avalon.category;


import avalon.category.enums.CategoryStatus;

import static avalon.category.validation.CategoryValidation.*;
import static commonValidation.StringValidation.*;

public class Category {

    private String name;
    private String code;
    private String description;
    private String studyGuide;
    private CategoryStatus status = CategoryStatus.DISABLED;
    private int order;
    private String imageUrl;
    private String colorCode;

    public Category(String name, String code) {
        isBlankEmptyOrNull(name, "Nome da categoria é requerido, não pode ser vazio ou em branco");
        isValidCode(code, "Código da Categoria não é válido ou está null ou vazio - deve ter caracteres de a-z - algarismos de 0-9 - Único caractere especial permitido é o hífen");
        this.name = name;
        this.code = code;
    }

    public Category(String name, String code, String description, String studyGuide, CategoryStatus status, int order, String imageUrl, String colorCode) {
        this(name, code);
        isValidOrder(order);
        isValidColor(colorCode, "Cor não é válida, deve ser em formato hexadecimal");
        this.description = description;
        this.studyGuide = studyGuide;
        this.status = status;
        this.order = order;
        this.imageUrl = imageUrl;
        this.colorCode = colorCode;
    }

    public String getCode() {
        return code;
    }

    public int getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return  "Nome='" + name + '\n' +
                "Código='" + code + '\n' +
                "Descrição='" + description + '\n' +
                "Guia de estudo='" + studyGuide + '\n' +
                "status=" + status + '\n' +
                "Ordem=" + order + '\n' +
                "imageUrl='" + imageUrl + '\n' +
                "Cor em Hexadecimal='" + colorCode + '\'';
    }
}
