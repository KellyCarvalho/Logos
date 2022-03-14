import java.sql.SQLException;

import static Logos.course.DaoCourse.delete;
import static Logos.course.DaoCourse.turnPublic;

public class Program {

    public static void main(String[] args) throws SQLException {
       // System.out.println(insert());
       delete(" java-jpa-consultas3333333-avancadas-performance-modelos-complexos ");
        System.out.println("Linhas afetadas: "+ turnPublic());
    }
}
