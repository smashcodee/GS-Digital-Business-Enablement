package br.com.smashcode.babycare.controller.impl;

import br.com.smashcode.babycare.controller.IAccountRestController;
import br.com.smashcode.babycare.request.AccountRequest;
import br.com.smashcode.babycare.response.AccountResponse;
import br.com.smashcode.babycare.response.SuccessResponse;
import br.com.smashcode.babycare.service.IAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/account")
@Slf4j
public class AccountRestControllerImpl implements IAccountRestController {
    @Autowired
    private IAccountService service;

    @PostMapping("/signup")
    public ResponseEntity<AccountResponse> createAccount(AccountRequest request) {
        var response =  service.createAccount(request);
        log.info("[ ** POST ** /account/signup ]: 201 - Created.");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/email/change")
    public ResponseEntity<SuccessResponse> updateEmail(String email, String accountId) {
        UUID originalUID = UUID.fromString(accountId);
        service.updateEmail(originalUID, email);
        log.info("[ ** PATCH ** /account/email/change?email=[email]&accountId=[id] ]: 204 - No content.");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/password/change")
    public ResponseEntity<SuccessResponse> updatePassword(String password, String accountId) {
        UUID originalUID = UUID.fromString(accountId);
        service.updatePassword(originalUID, password);
        log.info("[ ** PATCH ** /account/password/change?password=[password]&accountId=[id] ]: 204 - No content.");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/username/change")
    public ResponseEntity<SuccessResponse> updateUsername(String username, String accountId) {
        UUID originalUID = UUID.fromString(accountId);
        service.updateUsername(originalUID, username);
        log.info("[ ** PATCH ** /account/username/change?username=[username]&accountId=[id] ]: 204 - No content.");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/fullname/change")
    public ResponseEntity<SuccessResponse> updateFullName(String fullName, String accountId) {
        UUID originalUID = UUID.fromString(accountId);
        service.updateFullName(originalUID, fullName);
        log.info("[ ** PATCH ** /account/fullname/change?fullname=[fullname]&accountId=[id] ]: 204 - No content.");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /* Admin Role */
    @GetMapping("/list")
    public ResponseEntity<List<AccountResponse>> listAccounts() {
        var response = service.listAccounts();
        log.info("[ ** GET ** /account/list ]: (ADMIN) 200 - Ok.");
        return ResponseEntity.ok(response);
    }
}
