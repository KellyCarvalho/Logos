package entities;

public class Type {
    private Explanation explanation;
    private Video video;
    private Question question;

    public Type(Explanation explanation) {
        this.explanation = explanation;

    }

    public Type(Video video) {
        this.video = video;
    }

    public Type(Question question) {
        this.question = question;
    }

    public Explanation getExplanation() {
        return explanation;
    }

    public void setExplanation(Explanation explanation) {
        this.explanation = explanation;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
