package br.com.smashcode.babycare.controller;

import br.com.smashcode.babycare.request.AccountRequest;
import br.com.smashcode.babycare.response.AccountResponse;
import br.com.smashcode.babycare.response.SuccessResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IAccountRestController {
    ResponseEntity<AccountResponse> createAccount(@RequestBody @Valid AccountRequest request);
    ResponseEntity<SuccessResponse> updateEmail(@RequestParam(name = "email") String email,
                                                @RequestParam(name = "accountId") String accountId);
    ResponseEntity<SuccessResponse> updatePassword(@RequestParam(name = "password") String password,
                                                   @RequestParam(name = "accountId") String accountId);
    ResponseEntity<SuccessResponse> updateUsername(@RequestParam(name = "username") String username,
                                                   @RequestParam(name = "accountId") String accountId);
    ResponseEntity<SuccessResponse> updateFullName(@RequestParam(name = "fullName") String fullName,
                                                   @RequestParam(name = "accountId") String accountId);
    ResponseEntity<List<AccountResponse>> listAccounts();
}
