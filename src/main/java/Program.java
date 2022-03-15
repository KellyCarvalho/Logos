import java.sql.SQLException;

import static Logos.course.DaoCourse.*;
import static Logos.utils.GenerateHtml.generateCoursePage;

public class Program {

    public static void main(String[] args) throws SQLException {
        System.out.println("Id da inserção: " + insert());
        delete(" java-jpa-consultas-avancadas-performance-modelos-complexos ");
        System.out.println("Linhas afetadas: " + turnPublic());
        generateCoursePage();
    }
}
