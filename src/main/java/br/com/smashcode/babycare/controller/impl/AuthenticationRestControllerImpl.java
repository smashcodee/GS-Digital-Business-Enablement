package br.com.smashcode.babycare.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.smashcode.babycare.controller.IAuthenticationRestController;
import br.com.smashcode.babycare.response.AuthRequest;
import br.com.smashcode.babycare.response.AuthResponse;
import br.com.smashcode.babycare.service.impl.TokenService;

@RestController
@RequestMapping("/account/signin")
public class AuthenticationRestControllerImpl implements IAuthenticationRestController{
    
    @Autowired
    private AuthenticationManager manager;
    
    @Autowired
    private TokenService tokenService;

    @Override
    @PostMapping
    public ResponseEntity<AuthResponse> signin(AuthRequest request) {
        Authentication usernamePassword = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        manager.authenticate(usernamePassword);
        var response = tokenService.generateToken(request);
        return ResponseEntity.ok(response);
    }
    
}
