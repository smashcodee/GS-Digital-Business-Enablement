package br.com.smashcode.babycare.exception;


import lombok.Getter;

@Getter
public class EmailAlreadyExistsException extends RuntimeException {
    private String messageError;

    public EmailAlreadyExistsException(String message) {
        super(message);
        this.messageError = message;
    }
}
