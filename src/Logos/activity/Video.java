package Logos.activity;

import Logos.section.Section;

import static Logos.activity.validation.VideoValidator.isValidDuration;
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

    public void setDurationInMinutes(int durationInMinutes) {
        isValidDuration(durationInMinutes, 1);
        this.durationInMinutes = durationInMinutes;
    }

    @Override
    public String toString() {
        return "Video = " + getTitle() + '\n' +
                "url=" + url + '\n' +
                "durationInMinutes=" + durationInMinutes + '\n' +
                "transcription='" + transcription;
    }
}
