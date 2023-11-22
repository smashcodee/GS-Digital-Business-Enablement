package br.com.smashcode.babycare.response;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String type;
    private String prefix;
    private UUID accountId;

    public AuthResponse() { super(); }
}
