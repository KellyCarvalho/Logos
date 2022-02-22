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

            public static List<Category> toReadCsvTocategories(String pathName) throws FileNotFoundException {
                List<Category> categories = new ArrayList<>();

                Scanner scanner = new Scanner(new File(pathName));

                scanner.nextLine();
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Scanner lineScanner = new Scanner(line);
                    lineScanner.useDelimiter(",");
                    String name = lineScanner.next();
                    String code = lineScanner.next().strip();
                    String order = lineScanner.next().strip();
                    String description = lineScanner.next();
                    boolean status = lineScanner.next().equals("ATIVA") ? true : false;
                    String icon = lineScanner.next();
                    String color = lineScanner.next();
                    int orderInt = order == "" ? 0 : Integer.parseInt(order);
                    if (!(name.equals("nome") && code.equals("codigo") && order.equals("ordem") && description.equals("descricao") && icon.equals("icone") && color.equals("cor"))) {
                        categories.add(new Category(name, code, description, status ? CategoryStatus.ACTIVE : CategoryStatus.DISABLED, orderInt, icon, color));
                    }
                }
                scanner.close();
                return categories;
            }

            public static Category filterCategoriesByCode(List<Category> categories, String categoryCode) {
                for (Category category : categories) {
                    if (category.getCode().equals(categoryCode)) {
                        return category;
                    }
                }
                return null;
            }

            /*
                public static void toGenerateHtml(List<Course> courses, List<SubCategory> subCategories, List<Category> categories) throws FileNotFoundException, UnsupportedEncodingException {
                    Collections.sort(subCategories, Comparator.comparing(SubCategory::getOrder));
                    Locale locale = new Locale("pr", "br");
                    Locale.setDefault(locale);
                    PrintStream ps = new PrintStream(new File("categories.html"), "UTF-16");

                    StringBuilder sb = new StringBuilder();

                    sb.append("<html>");

                    sb.append("<body  style=\"background-color:#2EE6AE;\">");


                    sb.append("<table>");

                    sb.append("<tr>");
                    sb.append("<th ><h1> Categoria</th><h1> ");
                    sb.append("<th><h1>  Descrição</h1> </th>");


                    sb.append("<th> <h1> Ícone</h1> </th>");


                    sb.append("<th><h1> Cor de Fundo</h1> </th>");

                    sb.append("<th> <h1>Cursos</h1> </th>");


                    sb.append("<th> <h1>Horas de Curso</h1> </th>");

                    sb.append("<th><h1> SubCategoria</h1> </th>");

                    sb.append("<tr>");

                    Collections.sort(categories, Comparator.comparing(Category::getOrder));
                    Collections.sort(subCategories, Comparator.comparing(SubCategory::getOrder));


                    categories.forEach(category -> {
                        sb.append("<tr> " + "<td style=\"padding:20px; text-align:center;\"> <h2> " + category.getName() + "</h2> </td>");

                        sb.append("<td style=\"padding:20px; text-align:center;\"> <h4> " + category.getDescription() + " </h4> </td>");

                        sb.append("<td><img width=100px; src=" + category.getImageUrl() + "></td>");

                        List<Course> coursesToCategory = courses.stream().filter(course -> course.getSubCategory().getCategory() == category).toList();

                        int totalHoursCourse = 0;

                        String subCategory = "";

                        String nameCourses = "";
                        String subCategoryDescription = "";

                        for (Course course : coursesToCategory) {
                            totalHoursCourse += course.getEstimatedTime();

                            nameCourses += (course != null ? course.getName() + ", " : nameCourses);

                            subCategory = course.getSubCategory().getStatus() == SubCategoryStatus.ACTIVE ? course.getSubCategory().getName() : subCategory;

                            subCategoryDescription = course.getSubCategory().getDescription();
                        }
                        sb.append("<td> <div style=\" padding:50px;  border-radius:50px; background-color:" + category.getColorCode() + "\"> <div></td>");
                        sb.append("<td> <div style=\"padding:20px; text-align:center;  font-size:30px\"> <h4>" + coursesToCategory.size() + "</h4> </div></td>");
                        sb.append("<th> <div  style=\"padding:20px; text-align:center;  font-size:30px\"> <h4> " + totalHoursCourse + "</h4>  </div></th>");
                        if (subCategory != "" && nameCourses != null) {
                            sb.append("<th><div style=\"padding:20px;\"> Subategoria: " + subCategory + "</br>" + " Cursos: " + nameCourses + "<br> Descrição: " + subCategoryDescription + " </div></th>");
                        }
                        sb.append("</tr>");
                    });
                    sb.append("</table>");

                    sb.append("</body>");

                    sb.append("</html>");

                    ps.println(sb);
                }
            */

            public static void toGenerateHtml(List<Course> courses, List<SubCategory> subCategories, List<Category> categories) throws FileNotFoundException, UnsupportedEncodingException {
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

                    List<Course> coursesToCategory = courses.stream().filter(course -> course.getSubCategory().getCategory() == category).toList();

                    int totalHoursCourse = 0;

                    String subCategory = "";

                    String nameCourses = "";

                    String subCategoryDescription = "";

                    for (Course course : coursesToCategory) {
                        totalHoursCourse += course.getEstimatedTime();

                        nameCourses += (course != null ? course.getName() + ", " : nameCourses);

                        subCategory = course.getSubCategory().getStatus() == SubCategoryStatus.ACTIVE ? course.getSubCategory().getName() : subCategory;

                        subCategoryDescription = course.getSubCategory().getDescription();
                    }

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
                                               
                                            
                                        """.formatted(category.getName(), category.getDescription().strip(), category.getImageUrl(),category.getColorCode(),coursesToCategory.size(),totalHoursCourse,(subCategory!=""?"SubCategoria: "+subCategory+"</br> Cursos: "+nameCourses+"</br>  Descrição: "+subCategoryDescription:""));


                    sb.append(text);

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
