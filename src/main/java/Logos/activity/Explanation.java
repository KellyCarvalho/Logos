package Logos.activity;

import Logos.section.Section;

import javax.persistence.Column;
import javax.persistence.Entity;

import static Logos.commonValidator.StringValidator.isNotBlankEmptyOrNull;

@Entity
public class Explanation extends Activity {

    @Column(columnDefinition = "TEXT")
    private String description;

    @Deprecated
    public Explanation() {
    }

    public Explanation(String title, String code, Section section, String description) {
        super(title, code, section);
        isNotBlankEmptyOrNull(description, "Descrição de explicação não pode ser nula ou vazia");
        this.description = description;
    }
}
