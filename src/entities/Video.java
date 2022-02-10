package entities;

import java.util.Date;

public class Video extends Activity {
    private Long id;
    private String url;
    private Date duration;
    private String transcription;

    public Video(Long id, String url, Date duration, String transcription) {
        this.id = id;
        this.url = url;
        this.duration = duration;
        this.transcription = transcription;
    }

    public Video(Long id, String url, Date duration, String transcription, Activity activity) {
        super(activity.getId(), activity.getTitle(), activity.getCode(), activity.isActive(), activity.getOrder(), activity.getType(), activity.getSection());
        this.id = id;
        this.url = url;
        this.duration = duration;
        this.transcription = transcription;
        activity.setType(new Type(new Video(id,url,duration,transcription)));
    }

    public Video(Video video, Activity activity) {
        super(activity.getId(), activity.getTitle(), activity.getCode(), activity.isActive(), activity.getOrder(), activity.getType(), activity.getSection());
        this.setId(video.getId());
        this.setUrl(video.getUrl());
        this.setDuration(video.getDuration());
        this.setTranscription(video.getTranscription());


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
