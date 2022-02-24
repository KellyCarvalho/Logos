package Logos.course;

import Logos.category.Category;
import Logos.subCategory.SubCategory;
import Logos.subCategory.enums.SubCategoryStatus;

import static Logos.commonValidator.ObjectValidator.isObjectValid;
import static Logos.commonValidator.StringValidator.isNotBlankEmptyOrNull;
import static Logos.commonValidator.StringValidator.isValidCode;
import static Logos.course.validation.CourseValidation.isValidEstimatedTime;

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

    public Course(String name, String code, int estimatedTime, boolean visibility, String targetAudience, String instructor, String courseProgramDescription, String skillsDeveloped, SubCategory subCategory) {
        this(name, code, estimatedTime, instructor, subCategory);
        this.visibility = visibility;
        this.targetAudience = targetAudience;
        this.courseProgramDescription = courseProgramDescription;
        this.skillsDeveloped = skillsDeveloped;

    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public SubCategoryStatus isActiveSubCategory() {
        return subCategory.getStatus();
    }

    public String getNameSubCategory() {

        return subCategory.getName();
    }
    public String getDescriptionSubCategory(){
        return subCategory.getDescription();
    }

    public String getName() {
        return name;
    }

    public Category getCategory(){
        return getSubCategory().getCategory();
    }

    @Override
    public String toString() {
        return "Course" + '\n' +
                "name='" + name + '\n' +
                "code='" + code + '\n' +
                "estimatedTime=" + estimatedTime + '\n' +
                "visibility=" + visibility + '\n' +
                "targetAudience='" + targetAudience + '\n' +
                "instructor='" + instructor + '\n' +
                "courseProgramDescription='" + courseProgramDescription + '\n' +
                "skillsDeveloped='" + skillsDeveloped + '\n' +
                "subCategory:" + subCategory + '\n';
    }
}
