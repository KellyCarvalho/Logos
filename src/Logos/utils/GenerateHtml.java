package Logos.utils;

import Logos.category.Category;
import Logos.course.Course;
import Logos.subCategory.SubCategory;
import Logos.subCategory.enums.SubCategoryStatus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static Logos.commonValidator.ObjectValidator.isObjectValid;
import static Logos.course.CourseService.*;

public class GenerateHtml {

    public static void generateHtml() {
    }

    public static void toGenerateHtml(List<Course> courses, List<SubCategory> subCategories, List<Category> categories) throws FileNotFoundException, UnsupportedEncodingException {
        isObjectValid(courses, "Lista de cursos não pode ser vazia");
        isObjectValid(subCategories, "Lista de Subcategorias não pode ser vazia");
        isObjectValid(categories, "Lista de Categorias não pode ser vazia");
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
                                            <td><div  style="padding:20px;">  %s</div></td>
                                            <td><div  style="padding:20px;">  %s</div></td>
                                            <td><img style="width:100px;height:100px;" src="%s"></td>                                                            
                                            <td><div style="background-color:%s; padding:40px; border-radius:40px"></div></td>
                                            <td> <div  style="padding:20px;"> %s</div></td>
                                             <td> <div  style="padding:20px;"> %s</div></td>   
                                               <td> <div  style="padding:20px;"> %s</div></td>   
                                              <td> <div  style="padding:20px;">
                                                    <dl><dd>%s</dd>                                                                         
                                             </dl> 
                                              </div></td>                                         
                                         </tr>
                                     
                    """.formatted(category.getName(), category.getDescription(), category.getImageUrl(),
                    category.getColorCode(), coursesToCategory.size(), getTotalCourseHours(coursesToCategory),
                    getCoursesNames(coursesToCategory),getSubCategoryName(coursesToCategory));

            sb.append(!(getSubCategoryName(coursesToCategory).isBlank()||getSubCategoryName(coursesToCategory).isBlank()||getSubCategoryName(coursesToCategory).equals(null))? text : "");

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
}
