package Logos.activity;


import Logos.section.Section;

import static Logos.activity.validation.ActivityValidation.isValidOrder;
import static commonValidation.ObjectValidation.isObjectValid;
import static commonValidation.StringValidation.isNotBlankEmptyOrNull;
import static commonValidation.StringValidation.isValidCode;

public abstract class Activity {
    private String title;
    private String code;
    private boolean active;
    private int order;
    private Section section;

    public Activity(String title, String code, Section section) {
        isNotBlankEmptyOrNull(title, "Título de atividade é requerida, não pode ser vazia ou nula");
        isValidCode(code, "Código de Atividade não é válido ou está null ou vazio - deve ter caracteres de a-z - algarismos de 0-9 - Único caractere especial permitido é o hífen");
        isObjectValid(section, "Seção de atividade é requerida, não pode ser vazia ou nula");
        this.title = title;
        this.code = code;
        this.section = section;
    }

    public Activity(String title, String code, boolean active, int order, Section section) {
        this(title, code, section);
        isValidOrder(order);
        this.active = active;
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    public String getTitle() {
        return title;
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
