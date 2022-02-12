package entities;


import static validation.ExplanationValidation.fieldsContainsValue;

public class Explanation extends Activity {
    private Long id;
    private String description;

    public Explanation(String title, String code, boolean active, int order, Section section,TypeActivity typeActivity, String description) {
        super(title, code, active, order, section,typeActivity);
        fieldsContainsValue(description);

        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
