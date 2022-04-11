package br.com.logos.subCategory;

import br.com.logos.category.Category;
import br.com.logos.commonValidator.ObjectValidator;
import br.com.logos.subCategory.enums.SubCategoryStatus;

import javax.persistence.*;
import java.util.Objects;

import static br.com.logos.commonValidator.StringValidator.doesCodeContainsOnlyLettersInLowerCaseAndHyphen;
import static br.com.logos.commonValidator.StringValidator.isNotBlankEmptyOrNull;
import static br.com.logos.subCategory.enums.SubCategoryStatus.ACTIVE;
import static br.com.logos.subCategory.enums.SubCategoryStatus.DISABLED;

@Entity
@Table(name = "Subcategory")
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "identifier_code")
    private String code;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(name = "study_guide", columnDefinition = "TEXT")
    private String studyGuide;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTIVE','DISABLED')")
    private SubCategoryStatus status = DISABLED;
    @Column(name = "position")
    private int order;
    @ManyToOne
    @JoinColumn(name = "fk_category")
    private Category category;

    @Deprecated
    public SubCategory() {
    }

    public SubCategory(String name, String code, Category category) {
        isNotBlankEmptyOrNull(name, "Nome da SubCategoria é requerido, não pode ser vazio ou nulo");
        doesCodeContainsOnlyLettersInLowerCaseAndHyphen(code, "Código da SubCategoria não é válido ou está null ou vazio - deve ter caracteres de a-z -" +
                "Único caractere especial permitido é o hífen");
        ObjectValidator.isObjectValid(category, "Categoria é obrigatória, não pode ser nula");
        this.name = name;
        this.code = code;
        this.category = category;
    }

    public SubCategory(String name, String code, String description, SubCategoryStatus status, int order, Category category) {
        this(name, code, category);
        this.description = description;
        this.status = status;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public Long getCategoryId(){
        return this.getCategory().getId();
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return ACTIVE.equals(this.status);
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public SubCategoryStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubCategory that = (SubCategory) o;
        return code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return '\n' + "Nome=" + name + '\n' +
                "Código='" + code + '\n' +
                "Descrição='" + description + '\n' +
                "Guia de Estudos='" + studyGuide + '\n' +
                "Status=" + status + '\n' +
                "Order=" + order + '\n' + '\n' +
                "Categoria: " + '\n' + category.toString();
    }
}
