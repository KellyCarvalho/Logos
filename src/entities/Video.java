package entities;

import java.util.Date;

public class Video extends  Activity {
    private Long id;
    private String url;
    private Date duration;
    private String transcription;

    public Video() {

    }
    public Video(Long id, String url, Date duration, String transcription) {
        this.id = id;
        this.url = url;
        this.duration = duration;
        this.transcription = transcription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }
}
