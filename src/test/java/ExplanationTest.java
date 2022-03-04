import Logos.activity.Explanation;
import Logos.category.Category;
import Logos.course.Course;
import Logos.section.Section;
import Logos.subCategory.SubCategory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExplanationTest {
    private Explanation explanation;
    private Course course;
    private Section section;

    @BeforeAll
    public void initializeDependenciesCompletes(){
        course = new Course("Java","java",12,"Paulo", new SubCategory("java","java",new Category("programação","programacao")));
        section= new Section("secao1","secao",course);
        explanation = new Explanation("Como o java funciona","java-funciona",section,"Entenda como o java funciona");
    }

    @BeforeAll
    public void initializeDependenciesNoCompletes(){
        course = new Course("","java",12,"Paulo", new SubCategory("java","java",new Category("programação","programacao")));
        section= new Section("secao1","secao",course);
        explanation = new Explanation("Como o java funciona","java-funciona",section,"Entenda como o java funciona");
    }

    @Test
    void constructorShouldThrowIllegalExceptionIfNameCourseIsEmpty(){
        assertThrows(IllegalArgumentException.class, ()->{

        });
    }
}
