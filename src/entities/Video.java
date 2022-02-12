package entities;

import static validation.VideoValidation.*;

public class Video extends Activity {
    private Long id;
    private String url;
    private int duration;
    private String transcription;

    public Video(String title, String code, boolean active, int order, Section section, String url, int duration, String transcription) {
        super(title, code, active, order, section);
         isValidCode(code);
         isValidDuration(duration);
         fieldsContainsValue(url);
        this.url = url;
        this.duration = duration;
        this.transcription = transcription;
    }

    public Video(String title, String code, boolean active, int order, Section section, String url) {
        super(title, code, active, order, section);
        fieldsContainsValue(url);
        isValidCode(code);
        this.url = url;
    }
}
