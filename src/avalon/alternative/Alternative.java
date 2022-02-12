package avalon.alternative;

import avalon.activity.Question;

import static avalon.alternative.validation.AlternativeValidation.fieldsContainsValue;
import static avalon.alternative.validation.AlternativeValidation.isValidOrder;

public class Alternative {
    private Long id;
    private String description;
    private int order;
    private boolean correct;
    private String explanationAnswer;
    private Question question;

    public Alternative(String description, int order, boolean correct, String explanationAnswer, Question question) {
        fieldsContainsValue(description,question);
        isValidOrder(order);
        this.description = description;
        this.order = order;
        this.correct = correct;
        this.explanationAnswer = explanationAnswer;
        this.question = question;
    }

    public Alternative(String description,boolean correct, Question question) {
        fieldsContainsValue(description,question);
        this.description = description;
        this.question = question;
        this.correct=correct;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getOrder() {
        return order;
    }

    public boolean isCorrect() {
        return correct;
    }

    public String getExplanationAnswer() {
        return explanationAnswer;
    }

    public Question getQuestion() {
        return question;
    }
}
