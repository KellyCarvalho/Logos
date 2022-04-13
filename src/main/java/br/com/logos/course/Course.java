package br.com.logos.course;

import br.com.logos.category.Category;
import br.com.logos.commonValidator.ObjectValidator;
import br.com.logos.commonValidator.StringValidator;
import br.com.logos.subCategory.SubCategory;
import javax.persistence.*;
import java.util.Objects;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "identifier_code")
    private String code;
    @Column(name = "estimated_time")
    private int estimatedTime;
    private boolean visibility;
    @Column(name = "target_audience")
    private String targetAudience;
    @Column(name = "instructor_name")
    private String instructorName;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(name = "developed_skills", columnDefinition = "TEXT")
    private String developedSkills;
    @ManyToOne
    @JoinColumn(name = "fk_subcategory")
    private SubCategory subCategory;

    @Deprecated
    public Course() {
    }

    public Course(String name, String code, int estimatedTime, String instructorName, SubCategory subCategory) {
        StringValidator.isValidCode(code, "Código do curso não é válido ou está null ou vazio - deve ter caracteres de a-z " +
                "- algarismos de 0-9 - Único caractere especial permitido é o hífen");
        StringValidator.isNotBlankEmptyOrNull(name, "Nome do curso é requerido, não pode ser vazio ou nulo");
        isValidEstimatedTime(estimatedTime, 1, 20);
        StringValidator.isNotBlankEmptyOrNull(instructorName, "Nome do instrutor não pode ser vazio");
        ObjectValidator.isObjectValid(subCategory, "SubCategoria é obrigatória e não pode estar nula");
        this.name = name;
        this.code = code;
        this.estimatedTime = estimatedTime;
        this.instructorName = instructorName;
        this.subCategory = subCategory;
    }

    public Course(String name, String code, int estimatedTime, boolean visibility, String targetAudience, String instructorName,
                  String description, String developedSkills, SubCategory subCategory) {
        this(name, code, estimatedTime, instructorName, subCategory);
        this.visibility = visibility;
        this.targetAudience = targetAudience;
        this.description = description;
        this.developedSkills = developedSkills;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getDevelopedSkills() {
        return developedSkills;
    }

    public Category getCategory() {
        return getSubCategory().getCategory();
    }

    public String getCategoryName() {
        return getCategory().getName();
    }


    private static void isValidEstimatedTime(int estimatedTime, int min, int max) {
        if (estimatedTime < min || estimatedTime > max)
            throw new IllegalArgumentException("Tempo estimado de curso não pode ser menor que " + min + " ou maior que " + max);
    }

    public Long getId() {
        return id;
    }

    public Long getCategoryId(){
        return this.getCategory().getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return code.equals(course.code);
    }

    public boolean isVisibility() {
        return visibility;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "Course" + '\n' + "name='" + name + '\n' + "code='" + code +
                '\n' + "estimatedTime=" + estimatedTime + '\n' +
                "visibility=" + visibility + '\n' +
                "targetAudience='" + targetAudience + '\n' +
                "instructor='" + instructorName + '\n' +
                "Description='" + description + '\n' +
                "skillsDeveloped='" + developedSkills + '\n'
                + "subCategory:" + subCategory + '\n';
    }
}
