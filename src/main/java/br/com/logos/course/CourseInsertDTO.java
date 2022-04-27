package br.com.logos.course;

import br.com.logos.subCategory.SubCategory;

import javax.validation.constraints.*;

public class CourseInsertDTO {

    @NotBlank(message = "Nome é requerido")
    private String name;
    @NotBlank(message = "Código é requerido")
    @Pattern(regexp = "[a-z0-9^-]+", message = "Código  inválido, não pode ter caracteres especiais, hífem é permitido, letras devem ser minúsculas")
    private String code;
    @Min(message = "tempo mínimo não pode ser menor que 1", value = 1L)
    @Max(message = "tempo máximo não pode ser maior que 20", value = 20L)
    private int estimatedTime;
    private boolean visibility;
    private String targetAudience;
    @NotBlank(message = "Nome do instrutor é requerido")
    private String instructorName;
    private String description;
    private String developedSkills;
    @NotNull(message = "SubCategoria é obrigatória")
    private SubCategory subCategory;

    @Deprecated
    public CourseInsertDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDevelopedSkills() {
        return developedSkills;
    }

    public void setDevelopedSkills(String developedSkills) {
        this.developedSkills = developedSkills;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public Course toEntity() {
        return new Course(this.name, this.code, this.estimatedTime,
                this.visibility, this.targetAudience, this.instructorName,
                this.description, this.developedSkills, this.subCategory);
    }
}
