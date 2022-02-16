package avalon.activity;


import avalon.section.Section;
import avalon.activity.enums.TypeActivity;


public class Explanation extends Activity {

    private String description;

    public Explanation(String title, String code, boolean active, int order, Section section, String description) {
        super(title, code, active, order, section);
        this.description = description;
    }



    public String getDescription() {
        return description;
    }
}
