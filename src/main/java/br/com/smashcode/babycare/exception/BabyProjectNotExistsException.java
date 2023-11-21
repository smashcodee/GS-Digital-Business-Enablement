package br.com.smashcode.babycare.exception;


import lombok.Getter;

@Getter
public class BabyProjectNotExistsException extends RuntimeException {
    private String messageError;

    public BabyProjectNotExistsException(String message) {
        super(message);
        this.messageError = message;
    }
}
