package Logos.alternative;

import Logos.activity.Question;

import static Logos.alternative.validation.AlternativeValidation.isValidOrder;
import static commonValidation.ObjectValidation.isObjectValid;
import static commonValidation.StringValidation.isNotBlankEmptyOrNull;

public class Alternative {
    private String description;
    private int order;
    private boolean correct;
    private String explanationAnswer;
    private Question question;

    //TODO identação, referenciar com o this o construtor principal toString()


    public Alternative(String description, boolean correct, Question question) {
        isNotBlankEmptyOrNull(description,"Descrição de alternativa é requerida, não pode ser nula ou vazia");
        isObjectValid(question,"Questão é obrigatória, não pode ser nula");
        this.description = description;
        this.correct = correct;
        this.question = question;
    }

    public Alternative(String description, int order, boolean correct, String explanationAnswer, Question question) {
        this(description,correct,question);
        isValidOrder(order);
        this.description = description;
        this.order = order;
        this.correct = correct;
        this.explanationAnswer = explanationAnswer;
        this.question = question;
    }

    public int getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "Alternative{" +
                "description='" + description + '\'' +
                ", order=" + order +
                ", correct=" + correct +
                ", explanationAnswer='" + explanationAnswer + '\'' +
                ", question=" + question +
                '}';
    }
}
