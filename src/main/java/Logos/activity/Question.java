package Logos.activity;

import Logos.activity.enums.TypeQuestion;
import Logos.section.Section;

import javax.persistence.*;

import static Logos.commonValidator.ObjectValidator.isObjectValid;
import static Logos.commonValidator.StringValidator.isNotBlankEmptyOrNull;

@Entity
public class Question extends Activity {

    @Column(columnDefinition = "TEXT")
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "type_question", columnDefinition = "ENUM('ACTIVE','DISABLED')")
    private TypeQuestion type;

    @Deprecated
    public Question() {
    }

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
