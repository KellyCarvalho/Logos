package avalon.activity;

import avalon.section.Section;

import static commonValidation.StringValidation.isNotBlankEmptyOrNull;

public class Explanation extends Activity {

    private String description;

    public Explanation(String title, String code, Section section, String description) {
        super(title, code, section);
        this.description = description;
    }

    public Explanation(String title, String code, boolean active, int order, Section section, String description) {
        super(title, code, active, order, section);
        isNotBlankEmptyOrNull(description, "Descrição de explicação é requerida, não pode vazia ou nula");
        this.description = description;
    }
}
