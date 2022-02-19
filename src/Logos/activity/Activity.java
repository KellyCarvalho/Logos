package Logos.activity;


import Logos.section.Section;
import commonValidation.ValidatorUtils;

import static commonValidation.ObjectValidation.isObjectValid;
import static commonValidation.StringValidation.isNotBlankEmptyOrNull;
import static commonValidation.StringValidation.isValidCode;
import static commonValidation.ValidatorUtils.isValidOrder;

public abstract class Activity {
    private String title;
    private String code;
    private Section section;
    private boolean active;
    private int order;


    public Activity(String title, String code, Section section) {
        isNotBlankEmptyOrNull(title, "Título de atividade é requerida, não pode ser vazia ou nula");
        isValidCode(code, "Código de Atividade não é válido ou está null ou vazio - deve ter caracteres de a-z - algarismos de 0-9 - Único caractere especial permitido é o hífen");
        isObjectValid(section, "Seção de atividade é requerida, não pode ser vazia ou nula");
        this.title = title;
        this.code = code;
        this.section = section;
    }

    public String getTitle() {
        return title;
    }

    public void setOrder(int order) {
       isValidOrder(order,"Ordem de atividade não pode ser menor que 0");
        this.order = order;
    }

    @Override
    public String toString() {
        return "Activity{" + '\n' +
                "title='" + title + '\n' +
                "code='" + code + '\n' +
                "active=" + active + '\n' +
                "order=" + order + '\n' +
                "section=" + section + '\n';
    }
}
