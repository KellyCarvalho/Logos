package entities;


import static validation.ActivityValidation.*;

public abstract class Activity {
    private Long id;
    private String title;
    private String code;
    private boolean active;
    private int order;
    private Section section;



    public Activity(Long id, String title, String code, boolean active, int order, Section section) {
        isValidCode(code);
        fieldsContainsValue(title, code, section);
        isValidOrder(order);
        this.id = id;
        this.title = title;
        this.code = code;
        this.active = active;
        this.order = order;
        this.section = section;
    }

    public Activity(String title, String code, boolean active, int order, Section section) {
        isValidCode(code);
        fieldsContainsValue(title, code, section);
        isValidOrder(order);
        this.title = title;
        this.code = code;
        this.active = active;
        this.order = order;
        this.section = section;
    }

    public Activity(Long id, String title, String code, Section section) {
        isValidCode(code);
        fieldsContainsValue(title, code, section);
        this.id = id;
        this.title = title;
        this.code = code;

    }

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }

    public boolean isActive() {
        return active;
    }

    public int getOrder() {
        return order;
    }

    public Section getSection() {
        return section;
    }
}
