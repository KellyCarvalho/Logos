package entities;

public class Explanation {
    private Long id;
    private Activity activity;
    private String description;

    public Explanation() {

    }

    public Explanation(Long id, Activity activity, String description) {
        this.id = id;
        this.activity = activity;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
