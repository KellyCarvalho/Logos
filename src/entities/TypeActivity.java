package entities;

public class TypeActivity {
    private Explanation explanation;
    private Video video;
    private Question question;

    public TypeActivity(Explanation explanation) {
        this.explanation = explanation;

    }

    public TypeActivity(Video video) {
        this.video = video;
    }

    public TypeActivity(Question question) {
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
