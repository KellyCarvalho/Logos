package br.com.logos.activity;

import br.com.logos.section.Section;
import br.com.logos.commonValidator.StringValidator;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Explanation extends Activity {

    @Column(columnDefinition = "TEXT")
    private String description;

    @Deprecated
    public Explanation() {
    }

    public Explanation(String title, String code, Section section, String description) {
        super(title, code, section);
        StringValidator.isNotBlankEmptyOrNull(description, "Descrição de explicação não pode ser nula ou vazia");
        this.description = description;
    }
}
