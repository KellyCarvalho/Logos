package br.com.logos.alternative;

import br.com.logos.activity.Question;
import br.com.logos.commonValidator.ObjectValidator;
import br.com.logos.commonValidator.StringValidator;

import javax.persistence.*;

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
    @JoinColumn(name = "question_id")
    private Question question;

    @Deprecated
    public Alternative() {
    }

    public Alternative(String description, boolean correct, Question question) {
        StringValidator.isNotBlankEmptyOrNull(description, "Descrição de alternativa é requerida, não pode ser nula ou vazia");
        ObjectValidator.isObjectValid(question, "Questão é obrigatória, não pode ser nula");
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
