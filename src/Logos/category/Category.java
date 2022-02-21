package Logos.category;


import Logos.category.enums.CategoryStatus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static Logos.commonValidator.StringValidator.*;

public class Category {

    private String name;
    private String code;
    private String description;
    private String studyGuide;
    private CategoryStatus status = CategoryStatus.DISABLED;
    private String order;
    private String imageUrl;
    private String colorCode;

    public Category(String name, String code) {
        isNotBlankEmptyOrNull(name, "Nome da categoria é requerido, não pode ser vazio ou em branco");
        isValidCode(code, "Código da Categoria não é válido ou está null ou vazio - deve ter caracteres de a-z - algarismos de 0-9 - Único caractere especial permitido é o hífen");
        this.name = name;
        this.code = code;
    }

    public Category(String name, String code, String description, CategoryStatus status, String order, String imageUrl, String colorCode) {
        this(name, code);
        isValidColor(colorCode, " Cor de categoria não é válida");
        this.description = description;
        this.status = status;
        this.order = order;
        this.imageUrl = imageUrl;
        this.colorCode = colorCode;
    }

    public static List<Category> toReadCsvTocategories(String pathName) throws FileNotFoundException {
        List<Category> categories = new ArrayList<>();
        Scanner scanner = new Scanner(new File(pathName));
        String name = "";
        String code = "";
        String order = "";
        String description = "";
        boolean status = false;
        String icon = "";
        String color = "";

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",");
            name = lineScanner.next();
            code = lineScanner.next();
            order = lineScanner.next();
            description = lineScanner.next();
            status = lineScanner.next().equals("ATIVA") ? true : false;
            icon = lineScanner.next();
            color = lineScanner.next();
            if (!(name.equals("nome") && code.equals("codigo") && order.equals("ordem") && description.equals("descricao") && icon.equals("icone") && color.equals("cor"))) {
                categories.add(new Category(name, code, description, status ? CategoryStatus.ACTIVE : CategoryStatus.DISABLED, order, icon, color));
            }
        }
        categories.forEach(category -> {
            System.out.println(category);
        });
        scanner.close();
        return categories;
    }

    public String getCode() {
        return code;
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
        return name.equals(category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
