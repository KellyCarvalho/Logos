package br.com.logos.course;

import br.com.logos.category.Category;
import br.com.logos.commonValidator.ObjectValidator;
import br.com.logos.commonValidator.StringValidator;
import br.com.logos.subCategory.SubCategory;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;

@Getter
@EqualsAndHashCode
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

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public int getSubCategoryOrder(){
        return subCategory.getOrder();
    }

    public Category getCategory() {
        return getSubCategory().getCategory();
    }

    public String getInstructorName() {
        return instructorName;
    }

    public String getCategoryCode() {
        return this.getCategory().getCode();
    }

    public String getSubCategoryCode() {
        return this.getSubCategory().getCode();
    }

    private static void isValidEstimatedTime(int estimatedTime, int min, int max) {
        if (estimatedTime < min || estimatedTime > max)
            throw new IllegalArgumentException("Tempo estimado de curso não pode ser menor que " + min + " ou maior que " + max);
    }

    public Long getId() {
        return id;
    }

    public Long getCategoryId() {
        return this.getCategory().getId();
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void update(CourseUpdateDTO courseUpdateDTO) {
        this.code = courseUpdateDTO.getCode();
        this.name = courseUpdateDTO.getName();
        this.estimatedTime = courseUpdateDTO.getEstimatedTime();
        this.instructorName = courseUpdateDTO.getInstructorName();
        this.visibility = courseUpdateDTO.isVisibility();
        this.description = courseUpdateDTO.getDescription();
        this.developedSkills = courseUpdateDTO.getDevelopedSkills();
        this.subCategory = courseUpdateDTO.getSubCategory();
        this.targetAudience = courseUpdateDTO.getTargetAudience();
    }
}
