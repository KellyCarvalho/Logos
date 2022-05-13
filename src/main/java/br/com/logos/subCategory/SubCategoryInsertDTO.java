package br.com.logos.subCategory;

import br.com.logos.category.Category;
import br.com.logos.subCategory.enums.SubCategoryStatus;
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
public class SubCategoryInsertDTO {

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
    private Category  category;

    public SubCategory toEntity(){
        return new SubCategory(this.name, this.code, this.description,
                this.isActive() ? SubCategoryStatus.ACTIVE : SubCategoryStatus.DISABLED, this.order, this.category);
    }
}
