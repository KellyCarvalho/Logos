import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.sql.*;

public class Program {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        String htmlHeader = """
                <html>
                  <head>
                  </head>    
                  <body>
                      <table>
                        <tr>
                          <th>Id</th>
                          <th>Nome</th>
                          <th>Tempo de finalização</th>
                          <th>Id da subcategoria</th>
                          <th>Nome da subcategoria</th>
                        </tr>                                            
                """;
        stringBuilder.append(htmlHeader);
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/LOGOS", "root", "")) {
            Statement statement = connection.createStatement();
            statement.execute("SELECT *,subcat.name  subcategoryName \n" +
                    "FROM `Course`  course\n" +
                    "INNER JOIN `Subcategory` subcat\n" +
                    "ON subcat.id = course.fk_subcategory\n" +
                    " WHERE course.visibility=1;");

            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int estimatedTime = resultSet.getInt("estimated_time");
                String subcategoryName = resultSet.getString("subcategoryName");
                int fkSubcategory = resultSet.getInt("fk_subcategory");
                try (PrintStream printStream = new PrintStream(new File("files/courses.html"), "UTF-16")) {
                    String body = """
                                <tr>
                                  <th>
                                    <p>%d</p>
                                  </th>
                                  <th>
                                    <p>%s</p>
                                  </th>
                                  <th>
                                    <p>%d</p>
                                  </th>
                                  <th>
                                    <p>%d</p>
                                  </th>
                                  <th>
                                    <p>%s</p>
                                  </th>
                                </tr>
                             
                            """.formatted(id, name, estimatedTime, fkSubcategory, subcategoryName);
                    stringBuilder.append(body);
                    printStream.println(stringBuilder);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            String htmlFooter = """
                     </table>
                    </body>
                    </html>
                    """;
            stringBuilder.append(htmlFooter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
