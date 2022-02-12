package entities;


import static validation.QuestionValidation.fieldsContainsValue;

public class Question extends Activity {
    private Long id;
    private String statement;
    private TypeQuestion typeQuestion;



    public Question(String title, String code, boolean active, int order, Section section, String statement, TypeQuestion typeQuestion) {
        super(title, code, active, order, section);
        fieldsContainsValue(statement);
        this.statement = statement;
        this.typeQuestion = typeQuestion;


    }

    public Long getId() {
        return id;
    }

    public String getStatement() {
        return statement;
    }

    public TypeQuestion getTypeQuestion() {
        return typeQuestion;
    }
}
