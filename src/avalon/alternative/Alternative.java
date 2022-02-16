package avalon.alternative;

import avalon.activity.Question;

import static avalon.alternative.validation.AlternativeValidation.isValidOrder;

public class Alternative {
    private String description;
    private int order;
    private boolean correct;
    private String explanationAnswer;
    private Question question;
//TODO identação, referenciar com o this o construtor principal toString()
    public Alternative(String description, Question question) {
    this.description = description;
    this.question = question;

}
    public Alternative(String description, int order, boolean correct, String explanationAnswer, Question question) {
        isValidOrder(order);
        this.description = description;
        this.order = order;
        this.correct = correct;
        this.explanationAnswer = explanationAnswer;
        this.question = question;
    }

    public String getDescription() {
        return description;
    }

    public int getOrder() {
        return order;
    }

}
