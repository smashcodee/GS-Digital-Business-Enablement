package br.com.smashcode.babycare.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRequest {
    private String email;
    private String password;

    public AuthRequest() { super(); }
}
