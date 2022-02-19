package Logos.category;


import Logos.category.enums.CategoryStatus;

import static commonValidation.StringValidation.isNotBlankEmptyOrNull;
import static commonValidation.StringValidation.isValidCode;
import static commonValidation.ValidatorUtils.isValidOrder;

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
        isNotBlankEmptyOrNull(name, "Nome da categoria é requerido, não pode ser vazio ou em branco");
        isValidCode(code, "Código da Categoria não é válido ou está null ou vazio - deve ter caracteres de a-z - algarismos de 0-9 - Único caractere especial permitido é o hífen");
        this.name = name;
        this.code = code;
    }

    public void setOrder(int order) {
        isValidOrder(order,"Ordem de categoria não pode ser menor que 1");

        this.order = order;
    }

    @Override
    public String toString() {
        return "Nome='" + name + '\n' +
                "Código='" + code + '\n' +
                "Descrição='" + description + '\n' +
                "Guia de estudo='" + studyGuide + '\n' +
                "status=" + status + '\n' +
                "Ordem=" + order + '\n' +
                "imageUrl='" + imageUrl + '\n' +
                "Cor em Hexadecimal='" + colorCode + '\'';
    }
}
