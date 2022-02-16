package avalon.section;

import avalon.course.Course;
import avalon.section.validation.SectionValidation;

import static avalon.section.validation.SectionValidation.isValidOrder;

public class Section {

    private String name;
    private String code;
    private int order;
    private boolean active;
    private boolean test;
    private Course course;


    public Section(String name, String code, Course course) {
        //TODO setters para o métodos não obrigatórios
        this.name = name;
        this.code = code;
        this.course = course;
    }
    //TODO trocar construtor para setters
    public Section(String name, String code, int order, boolean active, boolean test, Course course) {

        isValidOrder(order);
        this.name = name;
        this.code = code;
        this.order = order;
        this.active = active;
        this.test = test;
        this.course = course;
    }

//TODO toString()
    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getOrder() {
        return order;
    }

}
