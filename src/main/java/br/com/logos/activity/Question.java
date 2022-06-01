package br.com.logos.activity;

import br.com.logos.activity.enums.TypeQuestion;
import br.com.logos.section.Section;
import br.com.logos.commonValidator.ObjectValidator;
import br.com.logos.commonValidator.StringValidator;
import lombok.ToString;

import javax.persistence.*;

@ToString
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
        StringValidator.isNotBlankEmptyOrNull(description, "Descrição de questão é requerida, não pode ser vazia ou nula");
        ObjectValidator.isObjectValid(type, "Tipo de questão é requerida, não pode ser nula");
        this.description = description;
        this.type = type;
    }
}
