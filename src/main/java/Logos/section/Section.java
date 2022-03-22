package Logos.section;

import Logos.course.Course;

import javax.persistence.*;

import static Logos.commonValidator.ObjectValidator.isObjectValid;
import static Logos.commonValidator.StringValidator.isNotBlankEmptyOrNull;
import static Logos.commonValidator.StringValidator.isValidCode;
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
    @JoinColumn(name = "fk_course")
    private Course course;

    public Section(String name, String code, Course course) {
        isNotBlankEmptyOrNull(name, "Nome da seção é requerido, não pode ser vazio ou nulo");
        isValidCode(code, "Código da seção não é válido ou está null ou vazio - deve ter caracteres de a-z - " +
                "algarismos de 0-9 - Único caractere especial permitido é o hífen");
        isObjectValid(course, "Curso de seção é requerido e não pode ser nulo");
        this.name = name;
        this.code = code;
        this.course = course;
    }

    @Override
    public String toString() {
        return "Section" + '\n' +
                "name='" + name + '\n' +
                "code='" + code + '\n' +
                "order=" + order + '\n' +
                "active=" + active + '\n' +
                "test=" + test + '\n' +
                "course=" + course;
    }
}
