package br.com.smashcode.babycare.response;

import br.com.smashcode.babycare.model.UserAccountEntity;
import br.com.smashcode.babycare.model.UserProfileEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AccountResponse {
    private UUID codAccount;
    private String email;
    private String username;
    private Boolean success;
    private UUID codUserProfile;
    private String fullName;
    private LocalDateTime accountCreatedAt;

    public AccountResponse(UserProfileEntity profile) {
        this.codAccount = profile.getAccount().getCodAccount();
        this.email = profile.getAccount().getEmail();
        this.username = profile.getAccount().getUsername();
        this.success = true;
        this.codUserProfile = profile.getCodUserProfile();
        this.fullName = profile.getFullName();
        this.accountCreatedAt = profile.getAccount().getCreatedAt();
    }
}
