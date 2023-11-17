package br.com.smashcode.babycare.response.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldNotValidResponse {
    private String field;
    private String message;
}
