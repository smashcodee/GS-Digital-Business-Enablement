package br.com.smashcode.babycare.exception;


import lombok.Getter;

@Getter
public class UsernameAlreadyExistsException extends RuntimeException {
    private String messageError;

    public UsernameAlreadyExistsException(String message) {
        super(message);
        this.messageError = message;
    }
}
