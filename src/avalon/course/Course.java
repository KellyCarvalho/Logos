package avalon.course;

import avalon.subCategory.SubCategory;

import static avalon.course.validation.CourseValidation.*;

import static commonValidation.ObjectValidation.isObjectValid;
import static commonValidation.StringValidation.isBlankOrEmpty;
import static commonValidation.StringValidation.isValidCode;



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
        isValidEstimatedTime(estimatedTime,1,20);
        isBlankOrEmpty(instructor,"Nome do instrutor não pode ser vazio");
        isObjectValid(subCategory,"SubCategoria é obrigatória e não pode estar nula");
        this.name = name;
        this.code = code;
        this.estimatedTime = estimatedTime;
        this.instructor = instructor;
        this.subCategory = subCategory;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", estimatedTime=" + estimatedTime +
                ", visibility=" + visibility +
                ", targetAudience='" + targetAudience + '\'' +
                ", instructor='" + instructor + '\'' +
                ", courseProgramDescription='" + courseProgramDescription + '\'' +
                ", skillsDeveloped='" + skillsDeveloped + '\'' +
                ", subCategory=" + subCategory +
                '}';
    }
}
