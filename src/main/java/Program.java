import Logos.category.Category;

import static Logos.utils.GenerateSql.writeSql;

public class Program {
    public static void main(String[] args) {
        /*writeSql();
        //TODO testar os validadores e o service de course
        //TODO Se der tempo testar
       */
        String code ="java-oo";
        Category category = new Category("programacao","programacao");
        System.out.println(code.matches("[a-z-]+")); ;
        System.out.println(category);
    }
}
