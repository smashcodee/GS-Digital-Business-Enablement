package br.com.smashcode.babycare.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.smashcode.babycare.response.AuthRequest;
import br.com.smashcode.babycare.response.AuthResponse;

public interface IAuthenticationRestController {
    ResponseEntity<AuthResponse> signin(@RequestBody AuthRequest request);
}
