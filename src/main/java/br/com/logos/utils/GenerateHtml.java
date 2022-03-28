package br.com.logos.utils;

import br.com.logos.category.Category;
import br.com.logos.course.Course;
import br.com.logos.subCategory.SubCategory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class GenerateHtml {
    
    public static StringBuilder courseDataTable(List<Course> courses) {
        StringBuilder html = new StringBuilder();
        courses.forEach(course -> {
            String body = """
                        <tr>
                          <td>%d</td>
                          <td>%s</td>
                          <td>%s</td>
                          <td>%d</td>
                          <td>%s</td>
                          <td>%s</td>
                          <td>%s</td>
                        </tr>
                    """.formatted(course.getId(), course.getName(), course.getCode(), course.getEstimatedTime(),
                    course.getTargetAudience(), course.getDescription(), course.getDevelopedSkills());
            html.append(body);
        });
        String htmlFooter = """
                    </tbody>
                  </table>
                """;
        html.append(htmlFooter);
            return html;
}

    public static StringBuilder categoryDataTable(List<Category> categories) {
        StringBuilder sb = new StringBuilder();
        String htmlHeader = """
                  <h1>Categorias</h1>
                  <hr>
                    <table>
                      <thead>
                        <tr>
                          <th>Id</th>      
                          <th>Nome</th>
                          <th>Code</th>
                          <th>Description</th>
                          <th>Guia de Estudo</th>
                          <th>Status</th>
                          <th>Ordem</th>
                        </tr>  
                      </thead>
                      <tbody>
                """;
        sb.append(htmlHeader);
        categories.forEach(category -> {
            String body = """
                        <tr>
                          <td>%d</td>
                          <td>%s</td>
                          <td>%s</td>
                          <td>%s</td>
                          <td>%s</td>
                          <td>%s</td>
                          <td>%s</td>
                        </tr>
                    """.formatted(category.getId(), category.getName(), category.getCode(), category.getDescription(), category.getStudyGuide(), category.getStatus(), category.getOrder());
            sb.append(body);
        });
        String htmlFooter = """
                    </tbody>
                  </table> 
                """;
        sb.append(htmlFooter);

        return sb;
    }

    private static StringBuilder subCategoriesNamesWithoutDescriptionDataTable(List<String> subCategoriesNames){
        StringBuilder sb = new StringBuilder();
        String htmlHeader = """
                  <h1>Nome das subcategorias sem descrição</h1>
                  <hr>
                    <table>
                      <thead>
                        <tr>
                          <th>Nomes </th>
                        </tr>  
                      </thead>
                      <tbody>
                """;
        sb.append(htmlHeader);
        subCategoriesNames.forEach(name -> {
            String body = """
                        <tr>
                          <td>%s</td>
                        </tr>
                    """.formatted(name);
            sb.append(body);
        });
        String htmlFooter = """
                    </tbody>
                  </table> 
                """;
        sb.append(htmlFooter);

        return sb;
    }

    private static StringBuilder subCategoryDataTable(List<SubCategory> subCategories) {
        StringBuilder sb = new StringBuilder();
        String htmlHeader = """
                  <h1>SubCategorias</h1>
                  <hr>
                    <table>
                      <thead>
                        <tr>
                          <th>Id</th>      
                          <th>Nome</th>
                          <th>Code</th>
                          <th>Description</th>
                          <th>Guia de Estudo</th>
                          <th>Status</th>
                          <th>Ordem</th>
                        </tr>  
                      </thead>
                      <tbody>
                """;
        sb.append(htmlHeader);
        subCategories.forEach(subCategory -> {
            String body = """
                        <tr>
                          <td>%d</td>
                          <td>%s</td>
                          <td>%s</td>
                          <td>%s</td>
                          <td>%s</td>
                          <td>%s</td>
                          <td>%s</td>
                        </tr>
                    """.formatted(subCategory.getId(), subCategory.getName(), subCategory.getCode(), subCategory.getDescription(),
                    subCategory.getStudyGuide(), subCategory.getStatus(), subCategory.getOrder());
            sb.append(body);
        });
        return sb;
    }

    public static void writeHtml(List<Course> courses, List<SubCategory> subCategories,List<String> subcategoriesName, List<Category> categories) {
        try (PrintStream printStream = new PrintStream(new File("files/data.html"), "UTF-16")) {

            printStream.print(htmlHeader());
            printStream.println(courseDataTable(courses));
            printStream.println(categoryDataTable(categories));
            printStream.println(subCategoriesNamesWithoutDescriptionDataTable(subcategoriesName));
            printStream.println(subCategoryDataTable(subCategories));
            printStream.print(htmlFooter());
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static String htmlHeader(){
        return   """
                    <html>
                  <head>  
                  <style>
                  table, th, td {
                    border: 1px solid black;
                    border-collapse: collapse;
                  }
                  </style>
                  </head>    
                  <body>
                  <h1>Cursos</h1>
                  <hr>
                    <table>
                      <thead>
                        <tr>
                          <th>Id</th>      
                          <th>Nome</th>
                          <th>Code</th>
                          <th>Tempo de finalização</th>
                          <th>Público Alvo</th>
                          <th>Ementa</th>
                          <th>Habilidades Desenvolvidas</th>
                        </tr>  
                      </thead>
                      <tbody>
                    """;
    }

    private static String htmlFooter(){
        return  """
                    </tbody>
                  </table> 
                </body>
                </html>
                """;
    }
}
