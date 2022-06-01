package br.com.logos.section;

import br.com.logos.course.Course;
import br.com.logos.commonValidator.ObjectValidator;
import br.com.logos.commonValidator.StringValidator;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Entity
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "identifier_code")
    private String code;
    @Column(name = "position")
    private int order;
    private boolean active;
    private boolean test;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Deprecated
    public Section() {
    }

    public Section(String name, String code, Course course) {
        StringValidator.isNotBlankEmptyOrNull(name, "Nome da seção é requerido, não pode ser vazio ou nulo");
        StringValidator.isValidCode(code, "Código da seção não é válido ou está null ou vazio - deve ter caracteres de a-z - " +
                "algarismos de 0-9 - Único caractere especial permitido é o hífen");
        ObjectValidator.isObjectValid(course, "Curso de seção é requerido e não pode ser nulo");
        this.name = name;
        this.code = code;
        this.course = course;
    }
}
