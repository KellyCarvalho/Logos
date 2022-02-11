package entities;

public class Alternative {
    private Long id;
    private String description;
    private boolean correct;
    private String explanationAnswer;
    private Question question;

    public Alternative(Long id, String description, boolean correct, String explanationAnswer) {
        this.id = id;
        this.description = description;
        this.correct = correct;
        this.explanationAnswer = explanationAnswer;
    }

    public Alternative(Long id, String description, boolean correct, String explanationAnswer, Question question) {
        this.id = id;
        this.description = description;
        this.correct = correct;
        this.explanationAnswer = explanationAnswer;
        this.question = question;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public String getExplanationAnswer() {
        return explanationAnswer;
    }

    public void setExplanationAnswer(String explanationAnswer) {
        this.explanationAnswer = explanationAnswer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
