package Logos.activity;

import Logos.section.Section;

import static Logos.commonValidator.StringValidator.isNotBlankEmptyOrNull;

public class Explanation extends Activity {

    private String description;

    public Explanation(String title, String code, Section section, String description) {
        super(title, code, section);
        isNotBlankEmptyOrNull(description,"Descrição de explicação não pode ser nula ou vazia");
        this.description = description;
    }
}
