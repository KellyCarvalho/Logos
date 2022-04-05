package br.com.logos.commonValidator;

public class FormErrorDTO {

    private  String field;
    private String error;

    public FormErrorDTO(String field, String error) {
        this.field = field;
        this.error = error;
    }
}
