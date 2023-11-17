package br.com.smashcode.babycare.service;

import br.com.smashcode.babycare.request.AccountRequest;
import br.com.smashcode.babycare.response.AccountResponse;
import br.com.smashcode.babycare.response.SuccessResponse;

import java.util.List;
import java.util.UUID;

public interface IAccountService {
    /**
     * create account
     * update email
     * update password
     * update username
     * update full name
     * delete account
     * */

    AccountResponse createAccount(AccountRequest request);
    List<AccountResponse> listAccounts();
    SuccessResponse updateEmail(UUID accountId, String email);
    SuccessResponse updatePassword(UUID accountId, String password);
    SuccessResponse updateUsername(UUID accountId, String username);
    SuccessResponse updateFullName(UUID accountId, String fullName);
    SuccessResponse deleteAccount(UUID accountId);
}
