package Logos.course;

import Logos.category.Category;
import Logos.subCategory.SubCategory;
import Logos.subCategory.enums.SubCategoryStatus;

import java.util.List;
import java.util.stream.Collectors;

import static Logos.commonValidator.ObjectValidator.isObjectValid;
import static Logos.commonValidator.StringValidator.isNotBlankEmptyOrNull;
import static Logos.commonValidator.StringValidator.isValidCode;

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
        isValidCode(code, "Código do curso não é válido ou está null ou vazio - deve ter caracteres de a-z " +
                "- algarismos de 0-9 - Único caractere especial permitido é o hífen");
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

    public Course(String name, String code, int estimatedTime, boolean visibility, String targetAudience, String instructorName,
                  String courseProgramDescription, String developedSkills, SubCategory subCategory) {
        this(name, code, estimatedTime, instructorName, subCategory);
        this.visibility = visibility;
        this.targetAudience = targetAudience;
        this.courseProgramDescription = courseProgramDescription;
        this.developedSkills = developedSkills;

    }

    public static boolean isValidEstimatedTime(int estimatedTime, int min, int max) {
        if (estimatedTime < min || estimatedTime > max)
            throw new IllegalArgumentException("Tempo estimado de curso não pode ser menor que " + min + " ou maior que " + max);
        return true;
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

    public String getDescriptionSubCategory() {
        return subCategory.getDescription();
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return getSubCategory().getCategory();
    }

    public boolean isVisibility() {
        return visibility;
    }

    public static String isAnyPrivateCourse(List<Course> courses) {
        return courses.stream().filter(course -> !course.isVisibility()).map(course -> "Existem cursos Privados na lista de cursos")
                .findFirst().orElse("Não Existem cursos privados na lista de cursos");
    }

    public String getInstructorName() {
        return instructorName;
    }

    public static String getInstructorsListToCourses(List<Course> courses) {
        return courses.stream().map(course -> course.getInstructorName()).distinct().collect(Collectors.joining(", "));
    }


    @Override
    public String toString() {
        return "Course" + '\n' + "name='" + name + '\n' + "code='" + code +
                '\n' + "estimatedTime=" + estimatedTime + '\n' +
                "visibility=" + visibility + '\n' +
                "targetAudience='" + targetAudience + '\n' +
                "instructor='" + instructorName + '\n' +
                "courseProgramDescription='" + courseProgramDescription + '\n' +
                "skillsDeveloped='" + developedSkills + '\n'
                + "subCategory:" + subCategory + '\n';
    }
}
