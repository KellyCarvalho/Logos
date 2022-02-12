package entities;

import validation.SectionValidation;

import java.util.List;

import static validation.SectionValidation.fieldsContainsValue;
import static validation.SectionValidation.isValidOrder;

public class Section {
    private Long id;
    private String name;
    private String code;
    private  int order;
    private boolean active;
    private boolean test;
    private Course course;


    public Section(Long id, String name, String code, int order, boolean active, boolean test, Course course) {
        fieldsContainsValue(name,code,course);
        SectionValidation.isValidCode(code);
        isValidOrder(order);
        this.id = id;
        this.name = name;
        this.code = code;
        this.order = order;
        this.active = active;
        this.test = test;
        this.course = course;
    }

    public Section(String name, String code, Course course) {
        fieldsContainsValue(name,code,course);
        this.name = name;
        this.code = code;
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getOrder() {
        return order;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isTest() {
        return test;
    }

    public Course getCourse() {
        return course;
    }


}
