package entities;

import validation.ActivityValidation;

import static validation.ActivityValidation.isValidOrder;

public class Activity {
    private Long id;
    private String title;
    private String code;
    private boolean active;
    private int order;
    private Type type;
    private Section section;

    @Deprecated
    public Activity() {

    }

    public Activity(Long id, String title, String code, boolean active, int order, Type type, Section section) {
        ActivityValidation.toValidCode(code);
        isValidOrder(order);
        this.id = id;
        this.title = title;
        this.code = code;
        this.active = active;
        this.order = order;
        this.type = type;
        this.section = section;
    }

    public Activity(Long id, String title, String code, boolean active, int order) {
        ActivityValidation.fieldsContainsValue( title,  code,  type,  section);
        ActivityValidation.toValidCode(code);
        isValidOrder(order);
        this.id = id;
        this.title = title;
        this.code = code;
        this.active = active;
        this.order = order;
    }

    public Activity(Long id, String title, String code, boolean active, int order, Section section) {
        ActivityValidation.fieldsContainsValue( title,  code,  type,  section);
        ActivityValidation.toValidCode(code);
        isValidOrder(order);
        this.id = id;
        this.title = title;
        this.code = code;
        this.active = active;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {

        this.type = type;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}
