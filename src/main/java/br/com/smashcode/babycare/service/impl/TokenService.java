package br.com.smashcode.babycare.service.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.smashcode.babycare.model.UserAccountEntity;
import br.com.smashcode.babycare.repository.IUserAccountRepository;
import br.com.smashcode.babycare.response.AuthRequest;
import br.com.smashcode.babycare.response.AuthResponse;

@Service
public class TokenService {
    @Autowired
    private IUserAccountRepository userAccountRepository;

    @Value("${jwt.secret}")
    private String secret;
    private String JWT_ISSUER = "NinusRestIssuer";

    public AuthResponse generateToken(AuthRequest request) {
        UserAccountEntity account = userAccountRepository.findByEmail(request.getEmail()).get();
        
        Algorithm alg = Algorithm.HMAC256(secret);
        var token = JWT.create()
            .withSubject(account.getEmail())
            .withIssuer(JWT_ISSUER)
            .withExpiresAt(Instant.now().plus(7, ChronoUnit.DAYS))
            .sign(alg);

        var response = new AuthResponse(token, "JWT", "Bearer", account.getCodAccount());
        return response;
    }

    public UserAccountEntity getAccountByToken(String token) {
        Algorithm alg = Algorithm.HMAC256(secret);
        var email = JWT.require(alg)
            .withIssuer(JWT_ISSUER)
            .build()
            .verify(token)
            .getSubject();

        var response = userAccountRepository.findByEmail(email).get();
        return response;
    }
}