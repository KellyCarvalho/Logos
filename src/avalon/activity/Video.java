package avalon.activity;

import avalon.section.Section;
import avalon.activity.enums.TypeActivity;

import static avalon.activity.validation.VideoValidation.*;

public class Video extends Activity {
    private String url;
    private int durationInMinutes;
    private String transcription;
    //TODO principal construtor como obrigátorio e referenciar ele nos outros construtores se usar sobrecarga

    public Video(String title, String code, boolean active, int order, Section section, String url) {
        super(title, code, active, order, section);
        isValidCode(code);
        this.url = url;
    }
    public Video(String title, String code, boolean active, int order, Section section, String url, int durationInMinutes, String transcription) {
        this(title, code, active, order, section,url);
        isValidDuration(durationInMinutes);
        this.durationInMinutes = durationInMinutes;
        this.transcription = transcription;
    }



    public String getUrl() {
        return url;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public String getTranscription() {
        return transcription;
    }
}
