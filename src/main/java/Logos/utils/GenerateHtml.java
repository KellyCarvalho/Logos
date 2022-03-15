package Logos.utils;

import Logos.category.Category;
import Logos.course.Course;
import Logos.subCategory.SubCategory;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static Logos.course.CourseService.*;
import static Logos.course.DaoCourse.courses;

public class GenerateHtml {

    public static void generateCategoryPage(List<Course> courses, List<SubCategory> subCategories, List<Category> categories) {
        StringBuilder sb = new StringBuilder();
        try (PrintStream ps = new PrintStream(new File("categories.html"), "UTF-16");) {
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
                                    <th>Cursos</th>
                                    <th>SubCategorias</th>
                                </tr>
                        </div>
                    """;
            sb.append(textHeader);
            categories.forEach(category -> {

                List<Course> coursesToCategory = courses.stream().filter(course -> course.getCategory() == category).toList();

                String text = """
                        <tr>
                            <td>
                                <div style="padding:20px;"> %s</div>
                            </td>
                            <td>
                                <div style="padding:20px;"> %s</div>
                            </td>
                            <td><img style="width:100px;height:100px;" src="%s"></td>
                            <td>
                                <div style="background-color:%s; padding:40px; border-radius:40px"></div>
                            </td>
                            <td>
                                <div style="padding:20px;"> %s</div>
                            </td>
                            <td>
                                <div style="padding:20px;"> %s</div>
                            </td>
                            <td>
                                <div style="padding:20px;"> %s</div>
                            </td>
                            <td>
                                <div style="padding:20px;">
                                    <dl>
                                        <dd>%s</dd>
                                    </dl>
                                </div>
                            </td>
                        </tr>
                                                                 
                                                """.formatted(category.getName(), category.getDescription(), category.getImageUrl(),
                        category.getColorCode(), coursesToCategory.size(), getTotalCourseHours(coursesToCategory),
                        getCoursesNames(coursesToCategory), getSubCategoryName(coursesToCategory));

                sb.append(!(getSubCategoryName(coursesToCategory).isBlank() || getSubCategoryName(coursesToCategory).isBlank() ||
                        getSubCategoryName(coursesToCategory).equals(null)) ? text : "");

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
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao gerar o arquivo html, certifique de que não faltou nenhuma lista a " +
                    "ser referenciada, pois é necessário que a lista de categorias, subcategorias e cursos sejam passadas obrigatoriamente para gerar o arquivo");
        }
    }

    public static void generateCoursePage() {
        StringBuilder sb = new StringBuilder();
        String htmlHeader = """
                <html>
                  <head>
                  </head>    
                  <body style="padding:20px;background-color:#FFC0CB">
                  <h1>Cursos</h1>
                  <hr>
                      <table width="467"  style="background-color:#DB7093;border-radius:20px;border:2px;">
                        <tr>
                          <th style="padding:20px;">Id</th>      
                          <th style="padding:20px;">Nome</th>
                          <th style="padding:20px;">Tempo de finalização</th>
                          <th style="padding:20px;">Id da subcategoria</th>
                          <th style="padding:20px;">Nome da subcategoria</th>
                        </tr>                       
                """;
        sb.append(htmlHeader);
        List<Course> courses = courses();
        try (PrintStream printStream = new PrintStream(new File("files/courses.html"), "UTF-16")) {
            courses.forEach(course -> {
                String body = """
                            <tr>
                              <th style="padding:20px;">
                                <p>%d</p>
                              </th>
                              <th style="padding:20px;">
                                <p>%s</p>
                              </th>
                              <th style="padding:20px;">
                                <p>%d</p>
                              </th>
                              <th style="padding:20px;">
                                <p>%d</p>
                              </th>
                              <th style="padding:20px;">
                                <p>%s</p>
                              </th>
                            </tr>
                        """.formatted(course.getId(), course.getName(), course.getEstimatedTime(), course.getSubcategoryId(), course.getSubCategoryName());
                sb.append(body);
            });
            String htmlFooter = """
                     </table>
                    </body>
                    </html>
                    """;
            sb.append(htmlFooter);
            printStream.println(sb);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        courses.forEach(course -> {

        });
    }
}
