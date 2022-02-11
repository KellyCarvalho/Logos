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






}
