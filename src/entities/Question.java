package entities;

public class Question extends Activity {
    private Long id;
    private String statement;

    public Question(Long id, String statement){
        super();
        this.id = id;
        this.statement = statement;
    }

    public Question(Long id, String statement, Activity activity) {
        super(activity.getId(), activity.getTitle(), activity.getCode(), activity.isActive(), activity.getOrder(), activity.getType(), activity.getSection());
        this.id = id;
        this.statement = statement;
        activity.setType(new Type(new Question( id,statement)));
    }

    public Question(Question question, Activity activity) {
        super(activity.getId(), activity.getTitle(), activity.getCode(), activity.isActive(), activity.getOrder(), activity.getType(), activity.getSection());
        this.id = question.getId();
        this.statement = question.getStatement();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }
}
