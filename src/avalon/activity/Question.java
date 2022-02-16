package avalon.activity;


import avalon.activity.enums.TypeQuestion;
import avalon.section.Section;

import static commonValidation.ObjectValidation.isObjectValid;
import static commonValidation.StringValidation.isNotBlankEmptyOrNull;


public class Question extends Activity {
    private String description;
    private TypeQuestion type;

    public Question(String title, String code, Section section, String description, TypeQuestion type) {
        super(title, code, section);
        isNotBlankEmptyOrNull(description, "Descrição de questão é requerida, não pode ser vazia ou nula");
        isObjectValid(type, "Tipo de questão é requerida, não pode ser nula ou vazia");
        this.description = description;
        this.type = type;
    }

    public Question(String title, String code, boolean active, int order, Section section, String description, TypeQuestion type) {
        super(title, code, active, order, section);
        isNotBlankEmptyOrNull(description, "Descrição de questão é requerida, não pode ser vazia ou nula");
        isObjectValid(type, "Tipo de questão é requerida, não pode ser nula ou vazia, tipo de questão é do tipo enum (TypeQuestion)");
        this.description = description;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Question" + '\n' +
                "description='" + description + '\n' +
                ", type=" + type;
    }
}
