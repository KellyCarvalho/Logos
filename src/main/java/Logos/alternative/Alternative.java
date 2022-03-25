package Logos.alternative;

import Logos.activity.Question;

import javax.persistence.*;

import static Logos.commonValidator.ObjectValidator.isObjectValid;
import static Logos.commonValidator.StringValidator.isNotBlankEmptyOrNull;

@Entity
public class Alternative {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(name = "position")
    private int order;
    private boolean correct;
    @Column(name = "explanation_answer")
    private String explanationAnswer;
    @ManyToOne
    @JoinColumn(name = "fk_question")
    private Question question;

    @Deprecated
    public Alternative() {
    }

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
