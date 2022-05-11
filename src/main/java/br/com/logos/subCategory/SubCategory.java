package br.com.logos.subCategory;

import br.com.logos.category.Category;
import br.com.logos.commonValidator.ObjectValidator;
import br.com.logos.course.Course;
import br.com.logos.subCategory.enums.SubCategoryStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static br.com.logos.commonValidator.StringValidator.doesCodeContainsOnlyLettersInLowerCaseAndHyphen;
import static br.com.logos.commonValidator.StringValidator.isNotBlankEmptyOrNull;
import static br.com.logos.subCategory.enums.SubCategoryStatus.ACTIVE;
import static br.com.logos.subCategory.enums.SubCategoryStatus.DISABLED;

@Getter
@EqualsAndHashCode
@Entity
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
    private Category category;
    @OneToMany(mappedBy = "subCategory", fetch = FetchType.EAGER)
    private List<Course> courses = new ArrayList<>();

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

    public SubCategory(SubCategoryInsertDTO subCategoryInsertDTO){
        this(subCategoryInsertDTO.getName(), subCategoryInsertDTO.getCode(), subCategoryInsertDTO.getDescription(),
                subCategoryInsertDTO.isActive() ? ACTIVE : DISABLED, subCategoryInsertDTO.getOrder(),
                subCategoryInsertDTO.getCategory());
    }

    public Long getCategoryId(){
        return this.category.getId();
    }

    public String getCategoryCode(){
        return this.category.getCode();
    }

    public boolean isActive() {
        return ACTIVE.equals(this.status);
    }

    public void update(SubCategoryUpdateDTO subCategoryUpdateDTO) {
        this.name = subCategoryUpdateDTO.getName();
        this.code = subCategoryUpdateDTO.getCode();
        this.description = subCategoryUpdateDTO.getDescription();
        this.studyGuide = subCategoryUpdateDTO.getStudyGuide();
        this.order = subCategoryUpdateDTO.getOrder();
        this.status = subCategoryUpdateDTO.isActive() ? SubCategoryStatus.ACTIVE : SubCategoryStatus.DISABLED;
        this.category = subCategoryUpdateDTO.getCategory();
    }

    public void disable() {
        this.status = SubCategoryStatus.DISABLED;
    }
}
