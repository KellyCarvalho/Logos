package Logos.course;

import Logos.subCategory.SubCategory;

import static Logos.course.validation.CourseValidation.isValidEstimatedTime;
import static Logos.commonValidator.ObjectValidator.isObjectValid;
import static Logos.commonValidator.StringValidator.isNotBlankEmptyOrNull;
import static Logos.commonValidator.StringValidator.isValidCode;

public class Course {

    private String name;
    private String code;
    private int estimatedTime;
    private boolean visibility;
    private String targetAudience;
    private String instructor;
    private String courseProgramDescription;
    private String skillsDeveloped;
    private SubCategory subCategory;

    public Course(String name, String code, int estimatedTime, String instructor, SubCategory subCategory) {
        isValidCode(code, "Código do curso não é válido ou está null ou vazio - deve ter caracteres de a-z - algarismos de 0-9 - Único caractere especial permitido é o hífen");
        isNotBlankEmptyOrNull(name, "Nome do curso é requerido, não pode ser vazio ou nulo");
        isValidEstimatedTime(estimatedTime, 1, 20);
        isNotBlankEmptyOrNull(instructor, "Nome do instrutor não pode ser vazio");
        isObjectValid(subCategory, "SubCategoria é obrigatória e não pode estar nula");
        this.name = name;
        this.code = code;
        this.estimatedTime = estimatedTime;
        this.instructor = instructor;
        this.subCategory = subCategory;
    }

    @Override
    public String toString() {
        return "Course" +
                "name='" + name + '\n' +
                ", code='" + code + '\n' +
                ", estimatedTime=" + estimatedTime + '\n' +
                ", visibility=" + visibility +
                ", targetAudience='" + targetAudience + '\n' +
                ", instructor='" + instructor + '\n' +
                ", courseProgramDescription='" + courseProgramDescription + '\n' +
                ", skillsDeveloped='" + skillsDeveloped + '\n' +
                ", subCategory=" + subCategory +
                '}';
    }
}
