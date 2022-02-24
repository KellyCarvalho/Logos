package Logos.alternative;

import Logos.activity.Question;

import static commonValidator.ObjectValidator.isObjectValid;
import static commonValidator.StringValidator.isNotBlankEmptyOrNull;

public class Alternative {
    private String description;
    private int order;
    private boolean correct;
    private String explanationAnswer;
    private Question question;

    public Alternative(String description, boolean correct, Question question) {
        isNotBlankEmptyOrNull(description, "Descrição de alternativa é requerida, não pode ser nula ou vazia");
        isObjectValid(question, "Questão é obrigatória, não pode ser nula");
        this.description = description;
        this.correct = correct;
        this.question = question;
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
