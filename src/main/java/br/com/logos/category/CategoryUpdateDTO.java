package br.com.logos.category;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

import static br.com.logos.commonValidator.StringValidator.*;

@Getter
@Setter
public class CategoryUpdateDTO {

    private Long id;
    @NotBlank(message = "Nome não pode estar em branco")
    private String name;
    @NotBlank(message = "Código não pode estar em branco")
    @Pattern(regexp = "[[a-z-]+]+", message = "Código  inválido, não pode ter caracteres especiais ou números, apenas o hífem é permitido, letras devem ser minúsculas")
    private String code;
    @PositiveOrZero(message = "Ordem deve ter valor positivo ou 0")
    private int order;
    private String studyGuide;
    private String description;
    private boolean active;
    private String imageUrl;
    @Pattern(regexp = "^#([a-fA-F0-9]){6}?$|^[\s]*$", message = "cor inválida")
    private String colorCode;

    @Deprecated
    public CategoryUpdateDTO(){
    }

    public CategoryUpdateDTO(Category category) {
        isValidColor(category.getColorCode(), "Cor não é válida");
        isValidCode(category.getCode(), "Código não é válido");
        isNotBlankEmptyOrNull(category.getName(), "Nome é requerido");
        this.id = category.getId();
        this.name = category.getName();
        this.code = category.getCode();
        this.order = category.getOrder();
        this.studyGuide = category.getStudyGuide();
        this.description = category.getDescription();
        this.active = category.isActive();
        this.imageUrl = category.getImageUrl();
        this.colorCode = category.getColorCode();
    }
}
