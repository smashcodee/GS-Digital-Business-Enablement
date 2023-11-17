package br.com.smashcode.babycare.exception;


import lombok.Getter;

@Getter
public class DateInvalidException extends RuntimeException {
    private String messageError;

    public DateInvalidException(String message) {
        super(message);
        this.messageError = message;
    }
}
