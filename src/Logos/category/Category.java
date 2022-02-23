package Logos.category;


import Logos.category.enums.CategoryStatus;
import Logos.course.Course;
import Logos.subCategory.SubCategory;
import Logos.subCategory.enums.SubCategoryStatus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

import static Logos.commonValidator.ObjectValidator.isObjectValid;
import static Logos.commonValidator.StringValidator.*;

public class Category {

    private String name;
    private String code;
    private String description;
    private String studyGuide;
    private CategoryStatus status = CategoryStatus.DISABLED;
    private int order;
    private String imageUrl;
    private String colorCode;

    public Category(String name, String code) {
        isNotBlankEmptyOrNull(name, "Nome da categoria é requerido, não pode ser vazio ou em branco");
        isValidCode(code, "Código da Categoria não é válido ou está null ou vazio - deve ter caracteres de a-z - algarismos de 0-9 - Único caractere especial permitido é o hífen");
        this.name = name;
        this.code = code;
    }

    public Category(String name, String code, String description, CategoryStatus status, int order, String imageUrl, String colorCode) {
        this(name, code);
        isValidColor(colorCode, " Cor de categoria não é válida");
        this.description = description;
        this.status = status;
        this.order = order;
        this.imageUrl = imageUrl;
        this.colorCode = colorCode;
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

    //TODO isolar o csv em outra classe apenas de leitura
    public static List<Category> readCsv(String pathName) throws FileNotFoundException {
        isNotBlankEmptyOrNull(pathName,"Caminho de arquivo deve ser preenchidpo");
        List<Category> categories = new ArrayList<>();
        Scanner scanner = new Scanner(new File(pathName));
        scanner.nextLine();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",");
            String name = lineScanner.hasNext() ? lineScanner.next().strip() : null;
            String code = lineScanner.hasNext() ? lineScanner.next().strip() : null;
            String order = lineScanner.next().strip();
            String description = lineScanner.next();
            CategoryStatus status = lineScanner.next().equals("ATIVA") ? CategoryStatus.ACTIVE  : CategoryStatus.DISABLED;
            String icon = lineScanner.next();
            String color = lineScanner.next();
            int orderInt = order == "" ? 0 : Integer.parseInt(order);
            //TODO verificar se é vazio em todos as variáveis
            if (name != null && code != null) {
                categories.add(new Category(name, code, description, status , orderInt, icon, color));
            }
        }
        scanner.close();
        return categories;
    }

    //TODO tentar não passar null - Isolar numa classe utils
    public static Category filterCategoriesByCode(List<Category> categories, String categoryCode) {
        for (Category category : categories) {
            if (category.getCode().equals(categoryCode)) {
                return category;
            }
        }
        return null;
    }

    //TODO isolar geração de Html em um genérico
    public static void toGenerateHtml(List<Course> courses, List<SubCategory> subCategories, List<Category> categories) throws FileNotFoundException, UnsupportedEncodingException {
        //TODO validar se as listas não são nulas
        isObjectValid(courses,"");
        StringBuilder sb = new StringBuilder();
        PrintStream ps = new PrintStream(new File("categories.html"), "UTF-16");
        Collections.sort(categories, Comparator.comparing(Category::getOrder));
        Collections.sort(subCategories, Comparator.comparing(SubCategory::getOrder));
        String textHeader = """
                  <html>
                                <head>
                                </head>
                                <body style="background-color:#BCE0F3;">
                             <div style="padding:20px;">
                              <hr>
                                    <table>
                                        <tr>
                                            <th>Categoria</th>
                                            <th>Descrição</th>
                                            <th>Ícone</th>
                                            <th>Cor de Fundo</th>
                                            <th>Nº Cursos</th>
                                            <th>Horas de Curso</th>
                                            <th>SubCategoria</th>
                                        </tr>
                                       
                                         </div>
                """;

        sb.append(textHeader);
        categories.forEach(category -> {
            //TODO tentar isolar o for para procurar cursos fora
            //TODO corrigir várias chamadas encadeadas
            List<Course> coursesToCategory = courses.stream().filter(course -> course.getSubCategory().getCategory() == category).toList();
            int totalHoursCourse = 0;
            String subCategory = "";
            String nameCourses = "";
            String subCategoryDescription = "";
            //TODO Isolar for para pegar valores
            //TODO só gerar html se a subcategoria estiver ativa
            for (Course course : coursesToCategory) {
                totalHoursCourse += course.getEstimatedTime();
                //TODO retirar a vírgula final
                nameCourses += (course != null ? course.getName() + ", " : nameCourses);
                //TODO chance grande de dar null pointer - fazer chamadas separadas em cada classe responsável
                subCategory = course.getSubCategory().getStatus() == SubCategoryStatus.ACTIVE ? course.getSubCategory().getName() : "";
                subCategoryDescription = course.getSubCategory().getDescription();
            }

            //TODO isolar ternário de validação de subcategoria vazia em um método
            String text = """
                                        <tr>
                                            <td><div  style="padding:20px;">  %s</div></td>
                                            <td><div  style="padding:20px;">  %s</div></td>
                                            <td><img style="width:100px;height:100px;" src="%s"></td>                                                            
                                            <td><div style="background-color:%s; padding:40px; border-radius:40px"></div></td>
                                            <td> <div  style="padding:20px;"> %s</div></td>
                                            <td><div  style="padding:20px;">  %s</div></td>
                                            <td><div  style="padding:20px;">  %s</div></td>
                                         </tr>
                                     
                    """.formatted(category.getName(), category.getDescription(), category.getImageUrl(),
                    category.getColorCode(), coursesToCategory.size(), totalHoursCourse,
                    (subCategory != "" ? "SubCategoria: " + subCategory + "</br> Cursos: " + nameCourses + "</br>  Descrição: " + subCategoryDescription : ""));
            if(subCategory!=""){
                sb.append(text);
            }


        });
        String textFoot = """
                         </table>
                          <hr>
                                </body>
                                </html>
                """;
        sb.append(textFoot);
        ps.println(sb);
        ps.flush();
        ps.close();
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
