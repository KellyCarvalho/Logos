package Logos.course;

import Logos.subCategory.SubCategory;

import static commonValidator.ObjectValidator.isObjectValid;
import static commonValidator.StringValidator.isNotBlankEmptyOrNull;
import static commonValidator.StringValidator.isValidCode;

public class Course {

    private String name;
    private String code;
    private int estimatedTime;
    private boolean visibility;
    private String targetAudience;
    private String instructorName;
    private String courseProgramDescription;
    private String developedSkills;
    private SubCategory subCategory;

    public Course(String name, String code, int estimatedTime, String instructorName, SubCategory subCategory) {
        isValidCode(code, "Código do curso não é válido ou está null ou vazio - deve ter caracteres de a-z - algarismos de 0-9 - Único caractere especial permitido é o hífen");
        isNotBlankEmptyOrNull(name, "Nome do curso é requerido, não pode ser vazio ou nulo");
        isValidEstimatedTime(estimatedTime, 1, 20);
        isNotBlankEmptyOrNull(instructorName, "Nome do instrutor não pode ser vazio");
        isObjectValid(subCategory, "SubCategoria é obrigatória e não pode estar nula");
        this.name = name;
        this.code = code;
        this.estimatedTime = estimatedTime;
        this.instructorName = instructorName;
        this.subCategory = subCategory;
    }

    public static boolean isValidEstimatedTime(int estimatedTime, int min, int max) {

        if (estimatedTime < min || estimatedTime > max)
            throw new IllegalArgumentException("Tempo estimado de curso não pode ser menor que " + min + " ou maior que " + max);

        return true;
    }

    @Override
    public String toString() {
        return "Course" +
                "name='" + name + '\n' +
                ", code='" + code + '\n' +
                ", estimatedTime=" + estimatedTime + '\n' +
                ", visibility=" + visibility +
                ", targetAudience='" + targetAudience + '\n' +
                ", instructor='" + instructorName + '\n' +
                ", courseProgramDescription='" + courseProgramDescription + '\n' +
                ", skillsDeveloped='" + developedSkills + '\n' +
                ", subCategory=" + subCategory +
                '}';
    }
}
