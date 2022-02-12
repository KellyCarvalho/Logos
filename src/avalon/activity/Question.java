package avalon.activity;


import avalon.section.Section;
import avalon.activity.enums.TypeActivity;
import avalon.activity.enums.TypeQuestion;

import static avalon.activity.validation.QuestionValidation.fieldsContainsValue;

public class Question extends Activity {
    private Long id;
    private String statement;
    private TypeQuestion typeQuestion;



    public Question(String title, String code, boolean active, int order, Section section, TypeActivity typeActivity, String statement, TypeQuestion typeQuestion) {
        super(title, code, active, order, section,typeActivity);
        fieldsContainsValue(statement);
        this.statement = statement;
        this.typeQuestion = typeQuestion;


    }


    public String getStatement() {
        return statement;
    }

    public TypeQuestion getTypeQuestion() {
        return typeQuestion;
    }
}
