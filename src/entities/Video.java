package entities;

import static validation.VideoValidation.fieldsContainsValue;
import static validation.VideoValidation.isValidDuration;

public class Video extends Activity {
    private Long id;
    private String url;
    private int duration;
    private String transcription;

    public Video(Long id, String url, int duration, String transcription) {
        fieldsContainsValue(url,transcription);
        isValidDuration(duration);
        this.id = id;
        this.url = url;
        this.duration = duration;
        this.transcription = transcription;
    }

}
