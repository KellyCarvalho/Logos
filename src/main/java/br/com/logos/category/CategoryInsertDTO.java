package br.com.logos.category;

import br.com.logos.category.enums.CategoryStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class CategoryInsertDTO {

    @NotBlank(message = "Nome não pode estar em branco")
    private String name;
    @NotBlank(message = "Código não pode estar em branco")
    @Pattern(regexp = "[a-z-]+", message = "Código  inválido, não pode ter caracteres especiais ou números, apenas o hífem é permitido, letras devem ser minúsculas")
    private String code;
    @PositiveOrZero(message = "Ordem deve ter valor positivo ou 0")
    private int order;
    private String studyGuide;
    private String description;
    private boolean active;
    private String imageUrl;
    @Pattern(regexp = "^#([a-fA-F0-9]){6}?$|^[\s]*$", message = "cor inválida")
    private String colorCode;

    public Category toEntity() {
        return new Category(this.name, this.code, this.description,
                this.studyGuide, this.active ? CategoryStatus.ACTIVE : CategoryStatus.DISABLED, this.order,
                this.imageUrl, this.colorCode);
    }
}
