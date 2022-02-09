package entities;

public class Type {
    private Explanation explanation;
    private Video video;
    private Question question;

    public Type() {

    }

    public Type(Explanation explanation) {
        this.explanation.setId(explanation.getId());
        this.explanation.setActivity(explanation.getActivity());
        this.explanation.setDescription(explanation.getDescription());
    }

    public Type(Video video) {
        this.video.setId(video.getId());
        this.video.setUrl(video.getUrl());
        this.video.setDuration(video.getDuration());
        this.video.setTranscription(video.getTranscription());
    }


}
