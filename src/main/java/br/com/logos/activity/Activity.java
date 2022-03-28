package br.com.logos.activity;

import br.com.logos.section.Section;
import br.com.logos.commonValidator.ObjectValidator;
import br.com.logos.commonValidator.StringValidator;

import javax.persistence.*;

@MappedSuperclass
public abstract class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(name = "identifier_code")
    private String code;
    @ManyToOne
    @JoinColumn(name = "fk_section")
    private Section section;
    private boolean active;
    @Column(name = "position")
    private int order;

    @Deprecated
    public Activity() {
    }

    public Activity(String title, String code, Section section) {
        StringValidator.isNotBlankEmptyOrNull(title, "Título de atividade é requerida, não pode ser vazia ou nula");
        StringValidator.isValidCode(code, "Código de Atividade não é válido ou está null ou vazio - deve ter caracteres de a-z - " +
                "algarismos de 0-9 - Único caractere especial permitido é o hífen");
        ObjectValidator.isObjectValid(section, "Seção de atividade é requerida, não pode ser vazia ou nula");
        this.title = title;
        this.code = code;
        this.section = section;
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
