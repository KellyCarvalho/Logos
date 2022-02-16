package avalon.activity;

import avalon.section.Section;

import static avalon.activity.validation.VideoValidation.isValidDuration;
import static commonValidation.StringValidation.isNotBlankEmptyOrNull;

public class Video extends Activity {
    private String url;
    private int durationInMinutes;
    private String transcription;

    public Video(String title, String code, Section section, String url) {
        super(title, code, section);
        isNotBlankEmptyOrNull(url, "Url de vídeo não pode ser vazia ou nula");
        this.url = url;
    }

    public Video(String title, String code, boolean active, int order, Section section, String url, int durationInMinutes, String transcription) {
        this(title, code, section, url);
        isValidDuration(durationInMinutes);
        this.durationInMinutes = durationInMinutes;
        this.transcription = transcription;
    }

    @Override
    public String toString() {
        return "Video = " + getTitle() + '\n' +
                "url=" + url + '\n' +
                "durationInMinutes=" + durationInMinutes + '\n' +
                "transcription='" + transcription;
    }
}
