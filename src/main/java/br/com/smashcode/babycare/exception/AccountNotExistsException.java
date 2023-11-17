package br.com.smashcode.babycare.exception;


import lombok.Getter;

@Getter
public class AccountNotExistsException extends RuntimeException {
    private String messageError;

    public AccountNotExistsException(String message) {
        super(message);
        this.messageError = message;
    }
}
