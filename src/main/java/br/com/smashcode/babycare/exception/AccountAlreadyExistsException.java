package br.com.smashcode.babycare.exception;


import lombok.Getter;

@Getter
public class AccountAlreadyExistsException extends RuntimeException {
    private String messageError;

    public AccountAlreadyExistsException(String message) {
        super(message);
        this.messageError = message;
    }
}
