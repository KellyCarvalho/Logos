package avalon.course;

import avalon.subCategory.SubCategory;
import static avalon.course.validation.CourseValidation.*;
import static commonValidation.ObjectValidation.*;
import static commonValidation.StringValidation.*;

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
        isValidCode(code,"Código do curso não é válido ou está null ou vazio - deve ter caracteres de a-z - algarismos de 0-9 - Único caractere especial permitido é o hífen");
        isBlankEmptyOrNull(name,"Nome do curso é requerido, não pode ser vazio ou nulo");
        isValidEstimatedTime(estimatedTime,1,20);
        isBlankEmptyOrNull(instructor,"Nome do instrutor não pode ser vazio");
        isObjectValid(subCategory,"SubCategoria é obrigatória e não pode estar nula");
        this.name = name;
        this.code = code;
        this.estimatedTime = estimatedTime;
        this.instructor = instructor;
        this.subCategory = subCategory;
    }

    public Course(String name, String code, int estimatedTime, boolean visibility, String targetAudience, String instructor, String courseProgramDescription, String skillsDeveloped, SubCategory subCategory) {
        this(name, code, estimatedTime, instructor, subCategory);
        this.visibility = visibility;
        this.targetAudience = targetAudience;
        this.instructor = instructor;
        this.courseProgramDescription = courseProgramDescription;
        this.skillsDeveloped = skillsDeveloped;
    }

    public String getCode() {
        return code;
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
