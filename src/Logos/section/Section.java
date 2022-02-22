package Logos.section;

import Logos.course.Course;

import static commonValidator.ObjectValidator.isObjectValid;
import static commonValidator.StringValidator.isNotBlankEmptyOrNull;
import static commonValidator.StringValidator.isValidCode;
import static commonValidator.UtilsValidator.isValidOrder;

public class Section {

    private String name;
    private String code;
    private int order;
    private boolean active;
    private boolean test;
    private Course course;

    public Section(String name, String code, Course course) {
        isNotBlankEmptyOrNull(name, "Nome da seção é requerido, não pode ser vazio ou nulo");
        isValidCode(code, "Código da seção não é válido ou está null ou vazio - deve ter caracteres de a-z - algarismos de 0-9 - Único caractere especial permitido é o hífen");
        isObjectValid(course, "Curso de seção é requerido e não pode ser nulo");
        this.name = name;
        this.code = code;
        this.course = course;
    }

    public void setOrder(int order) {
        isValidOrder(order, "Ordem de seção não pode ser menor que 1");
        this.order = order;
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
