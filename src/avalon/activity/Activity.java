package avalon.activity;


import avalon.section.Section;

import static avalon.activity.validation.ActivityValidation.*;

public abstract class Activity {
    private String title;
    private String code;
    private boolean active;
    private int order;
    private Section section;

    public Activity(String title, String code, Section section) {
        isValidCode(code);
        this.title = title;
        this.code = code;
    }

    public Activity(String title, String code, boolean active, int order, Section section) {
        this(title, code, section);
        isValidOrder(order);
        this.active = active;
        this.order = order;
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
