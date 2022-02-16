package avalon.activity;


import avalon.activity.enums.TypeQuestion;
import avalon.section.Section;


public class Question extends Activity {
    private String description;
    private TypeQuestion type;

    //TODO validar tipo obrigatório
    public Question(String title, String code, boolean active, int order, Section section,  String statement, TypeQuestion type) {
        super(title, code, active, order, section);
        this.description = statement;
        this.type = type;
    }


    public String getDescription() {
        return description;
    }

}
