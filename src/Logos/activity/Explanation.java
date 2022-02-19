package Logos.activity;

import Logos.section.Section;

public class Explanation extends Activity {

    private String description;

    public Explanation(String title, String code, Section section, String description) {
        super(title, code, section);
        this.description = description;
    }
}
