package Logos.subCategory;

import Logos.category.Category;
import Logos.subCategory.enums.SubCategoryStatus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Logos.category.Category.filterCategoriesByCode;
import static Logos.commonValidator.ObjectValidator.isObjectValid;
import static Logos.commonValidator.StringValidator.isNotBlankEmptyOrNull;
import static Logos.commonValidator.StringValidator.isValidCode;

public class SubCategory {

    private String name;
    private String code;
    private String description;
    private String studyGuide;
    private SubCategoryStatus status = SubCategoryStatus.DISABLED;
    private int order;
    private Category category;

    public SubCategory(String name, String code, Category category) {
        isNotBlankEmptyOrNull(name, "Nome da SubCategoria é requerido, não pode ser vazio ou nulo");
        isValidCode(code, "Código da SubCategoria não é válido ou está null ou vazio - deve ter caracteres de a-z - algarismos de 0-9 - Único caractere especial permitido é o hífen");
        isObjectValid(category, "Categoria é obrigatória, não pode ser nula");
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

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public int getOrder() {
        return order;
    }

    public SubCategoryStatus getStatus() {
        return status;
    }

    public static SubCategory filterSubCategoriesByCode(List<SubCategory> subCategories, String subCategoryCode) {

        for (SubCategory subCategory : subCategories) {
            if (subCategory.getCode().equals(subCategoryCode)) {
                return subCategory;
            }
        }

        return null;
    }

    public static List<SubCategory> toReadCsvToSubCategories(String pathName, List<Category> categories) throws FileNotFoundException {
        List<SubCategory> subCategories = new ArrayList<>();
        Scanner scanner = new Scanner(new File(pathName));

        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",");
            String name = lineScanner.next();
            String code = lineScanner.next();
            String order = lineScanner.next().strip();
            String description = lineScanner.next();
            boolean status = lineScanner.next().equals("ATIVA") ? true : false;
            String category = lineScanner.hasNext() ? lineScanner.next().strip() : null;
            int orderInt = order==""?0: Integer.parseInt(order);
            System.out.println(category);
            if (name != null && code != null && category != null) {
                subCategories.add(new SubCategory(name, code, description, status ? SubCategoryStatus.ACTIVE : SubCategoryStatus.DISABLED, orderInt, filterCategoriesByCode(categories, category)));
            }
        }

        return subCategories;
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
