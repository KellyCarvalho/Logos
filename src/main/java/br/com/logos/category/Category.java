package br.com.logos.category;


import br.com.logos.category.enums.CategoryStatus;
import br.com.logos.commonValidator.StringValidator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;

import static br.com.logos.category.enums.CategoryStatus.ACTIVE;
import static br.com.logos.category.enums.CategoryStatus.DISABLED;
import static br.com.logos.commonValidator.StringValidator.isValidColor;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Nome não pode estar em branco")
    private String name;
    @Column(name = "identifier_code")
    @NotBlank(message = "Código não pode estar em branco")
    @Pattern(regexp = "[[a-z-]+]+", message = "Código  inválido, não pode ter caracteres especiais ou números, apenas o hífem é perminido, letras devem ser minúsculas")
    private String code;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(name = "study_guide", columnDefinition = "TEXT")
    private String studyGuide;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTIVE','DISABLED')")
    private CategoryStatus status = DISABLED;
    @Column(name = "position")
    @PositiveOrZero(message = "Ordem deve ter valor positivo ou 0")
    private int order;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "color_code")
    @Pattern(regexp = "^#([a-fA-F0-9]){6}?$|^[\s]*$", message = "cor inválida")
    private String colorCode;

    @Deprecated
    public Category() {
    }

    public Category(String name, String code) {
        StringValidator.isNotBlankEmptyOrNull(name, "Nome da categoria é requerido, não pode ser vazio ou em branco");
        StringValidator.doesCodeContainsOnlyLettersInLowerCaseAndHyphen(code, "Código da Categoria não é válido ou está null ou vazio - deve ter caracteres de a-z - " +
                "Único caractere especial permitido é o hífen");
        this.name = name;
        this.code = code;
    }

    public Category(String name, String code, CategoryStatus status) {
        this(name, code);
        this.status = status;
    }

    public Category(String name, String code, String description, CategoryStatus status, int order, String imageUrl, String colorCode) {
        this(name, code, status);
        isValidColor(colorCode, "Cor de categoria não é válida");
        this.description = description;
        this.order = order;
        this.imageUrl = imageUrl;
        this.colorCode = colorCode;
    }

    public Category(String name, String code, String description, String studyGuide, CategoryStatus status, int order, String imageUrl, String colorCode) {
        this(name, code, description, status, order, imageUrl, colorCode);
        this.studyGuide = studyGuide;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getOrder() {
        return order;
    }

    public String getColorCode() {
        return colorCode;
    }

    public CategoryStatus getStatus() {
        return status;
    }

    public void setStatus(CategoryStatus status) {
        this.status = status;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public boolean isActive() {
        return ACTIVE.equals(this.getStatus());
    }

    public void update(CategoryUpdateDTO categoryUpdateDTO) {
        this.name = categoryUpdateDTO.getName();
        this.code = categoryUpdateDTO.getCode();
        this.description = categoryUpdateDTO.getDescription();
        this.studyGuide = categoryUpdateDTO.getStudyGuide();
        this.order = categoryUpdateDTO.getOrder();
        this.status = categoryUpdateDTO.isActive() ? ACTIVE : DISABLED;
        this.imageUrl = categoryUpdateDTO.getImageUrl();
        this.colorCode = categoryUpdateDTO.getColorCode();
    }

    public void disable(){
        this.status = DISABLED;
    }

    @Override
    public String toString() {
        return "Nome= " + name + '\n' +
                "Código= " + code + '\n' +
                "Descrição= " + description + '\n' +
                "Guia de estudo=" + studyGuide + '\n' +
                "status= " + status + '\n' +
                "Ordem= " + order + '\n' +
                "imageUrl= " + imageUrl + '\n' +
                "Cor em Hexadecimal= " + colorCode + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return code.equals(category.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
