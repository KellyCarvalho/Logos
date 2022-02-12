package avalon.activity;


import avalon.section.Section;
import avalon.activity.enums.TypeActivity;

import static avalon.activity.validation.ActivityValidation.*;

public abstract class Activity {
    private Long id;
    private String title;
    private String code;
    private boolean active;
    private int order;
    private Section section;
    private TypeActivity typeActivity;


    public Activity(Long id, String title, String code, boolean active, int order, Section section,  TypeActivity typeActivity) {
        isValidCode(code);
        fieldsContainsValue(title, code, section);
        isValidOrder(order);
        this.id = id;
        this.title = title;
        this.code = code;
        this.active = active;
        this.order = order;
        this.section = section;
        this.typeActivity=typeActivity;
    }

    public Activity(String title, String code, boolean active, int order, Section section,  TypeActivity typeActivity) {
        isValidCode(code);
        fieldsContainsValue(title, code, section);
        isValidOrder(order);
        this.title = title;
        this.code = code;
        this.active = active;
        this.order = order;
        this.section = section;
        this.typeActivity=typeActivity;
    }

    public Activity(Long id, String title, String code, Section section) {
        isValidCode(code);
        fieldsContainsValue(title, code, section);
        this.id = id;
        this.title = title;
        this.code = code;

    }

    public Long getId() {
        return id;
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

    public TypeActivity getTypeActivity() {
        return typeActivity;
    }
}
