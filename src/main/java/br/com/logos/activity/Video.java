package br.com.logos.activity;

import br.com.logos.section.Section;
import br.com.logos.commonValidator.StringValidator;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;

@ToString
@Entity
public class Video extends Activity {

    private String url;
    @Column(name = "duration_in_minutes")
    private int durationInMinutes;
    @Column(columnDefinition = "TEXT")
    private String transcription;

    @Deprecated
    public Video() {
    }

    public Video(String title, String code, Section section, String url) {
        super(title, code, section);
        StringValidator.isNotBlankEmptyOrNull(url, "Url de vídeo não pode ser vazia ou nula");
        this.url = url;
    }
}
