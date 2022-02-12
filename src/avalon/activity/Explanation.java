package avalon.activity;


import avalon.section.Section;
import avalon.activity.enums.TypeActivity;

import static avalon.activity.validation.ExplanationValidation.fieldsContainsValue;

public class Explanation extends Activity {
    private Long id;
    private String description;

    public Explanation(String title, String code, boolean active, int order, Section section, TypeActivity typeActivity, String description) {
        super(title, code, active, order, section,typeActivity);
        fieldsContainsValue(description);

        this.description = description;
    }



    public String getDescription() {
        return description;
    }
}
