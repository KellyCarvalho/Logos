package Logos.activity;

import Logos.activity.enums.TypeQuestion;
import Logos.section.Section;

import static commonValidator.ObjectValidator.isObjectValid;
import static commonValidator.StringValidator.isNotBlankEmptyOrNull;

public class Question extends Activity {
    private String description;
    private TypeQuestion type;

    public Question(String title, String code, Section section, String description, TypeQuestion type) {
        super(title, code, section);
        isNotBlankEmptyOrNull(description, "Descrição de questão é requerida, não pode ser vazia ou nula");
        isObjectValid(type, "Tipo de questão é requerida, não pode ser nula");
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
