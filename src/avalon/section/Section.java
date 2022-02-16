package avalon.section;

import avalon.course.Course;

import static avalon.section.validation.SectionValidation.*;
import static commonValidation.ObjectValidation.*;
import static commonValidation.StringValidation.*;

public class Section {

    private String name;
    private String code;
    private int order;
    private boolean active;
    private boolean test;
    private Course course;

    public Section(String name, String code, Course course) {
        isNotBlankEmptyOrNull(name, "Nome é requerido, não pode ser vazio ou nulo");
        isValidCode(code, "Código da seção não é válido ou está null ou vazio - deve ter caracteres de a-z - algarismos de 0-9 - Único caractere especial permitido é o hífen");
        isNotBlankEmptyOrNull(name, "Nome é requerido, não pode ser vazio ou nulo");
        isObjectValid(course, "Curso de seção é requerido e não pode ser nulo");
        this.name = name;
        this.code = code;
        this.course = course;
    }

    public Section(String name, String code, int order, boolean active, boolean test, Course course) {
        this(name, code, course);
        isValidOrder(order);
        this.name = name;
        this.code = code;
        this.order = order;
        this.active = active;
        this.test = test;
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

    public String getCode() {
        return code;
    }

    public int getOrder() {
        return order;
    }

}
