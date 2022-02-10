package entities;

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
        this.id = id;
        this.description = description;
        activity.setType( new Type(new Explanation(id,description)));

    }

    public Explanation(Explanation explanation,Activity activity) {
        super(activity.getId(), activity.getTitle(), activity.getCode(), activity.isActive(), activity.getOrder(), activity.getType(), activity.getSection());
        this.setId(explanation.getId());
        this.setDescription(explanation.getDescription());
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
