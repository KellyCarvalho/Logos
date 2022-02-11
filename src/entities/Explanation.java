package entities;

import static validation.ExplanationValidation.fieldsContainsValue;

public class Explanation extends Activity {
    private Long id;
    private String description;

    public Explanation(Long id, String description) {
        super();
        this.id = id;
        this.description = description;


    }

    public Explanation(Long id, String description, Activity activity) {
     super(activity.getId(), activity.getTitle(), activity.getCode(), activity.isActive(), activity.getOrder(), activity.getType(), activity.getSection());
    fieldsContainsValue(description);

        this.id = id;
        this.description = description;
        activity.setType( new Type(new Explanation(id,description)));

    }

    public Explanation(Long id, String title, String code, boolean active, int order, Type type, Section section, Long id1, String description) {
        super(id, title, code, active, order, type, section);
        this.id = id1;
        this.description = description;
    }

    public Explanation(Explanation explanation, Activity activity) {
        super(activity.getId(), activity.getTitle(), activity.getCode(), activity.isActive(), activity.getOrder(), activity.getType(), activity.getSection());
      fieldsContainsValue(description);
        this.id=(explanation.getId());
        this.description=(explanation.getDescription());
        activity.setType( new Type(explanation));
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




}
