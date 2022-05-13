package br.com.logos.course;

import br.com.logos.subCategory.SubCategory;
import lombok.*;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
@Getter
@Setter
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

    public Course toEntity() {
        return new Course(this.name, this.code, this.estimatedTime,
                this.visibility, this.targetAudience, this.instructorName,
                this.description, this.developedSkills, this.subCategory);
    }
}
