package br.com.logos.subCategory;

import br.com.logos.category.Category;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
@Getter
@Setter
public class SubCategoryUpdateDTO {

    private Long id;
    @NotBlank(message = "Nome é requerido")
    private String name;
    @Pattern(regexp = "[[a-z-]+]+", message = "Código  inválido, não pode ter caracteres especiais ou números, apenas o hífem é permitido, letras devem ser minúsculas")
    @NotBlank(message = "Codigo é requerido")
    private String code;
    private String description;
    private String studyGuide;
    private boolean active;
    @PositiveOrZero(message = "Ordem deve ter valor positivo ou 0")
    private int order;
    @NotNull(message = "Categoria não pode ser vazia")
    private Category category;

    public SubCategoryUpdateDTO(SubCategory subCategory) {
        this.id = subCategory.getId();
        this.name = subCategory.getName();
        this.code = subCategory.getCode();
        this.description = subCategory.getDescription();
        this.order = subCategory.getOrder();
        this.studyGuide = subCategory.getStudyGuide();
        this.active = subCategory.isActive();
        this.category = subCategory.getCategory();
    }
}
