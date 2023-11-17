package br.com.smashcode.babycare.utils;

import br.com.smashcode.babycare.model.UserAccountEntity;
import br.com.smashcode.babycare.repository.IUserAccountRepository;
import br.com.smashcode.babycare.request.AccountRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class UserAccountUtils {
    private static IUserAccountRepository userAccountRepository;

    public UserAccountUtils(IUserAccountRepository userAccountRepository){
        UserAccountUtils.userAccountRepository = userAccountRepository;
    }
    public static boolean userAccountExists(UUID accountId) {
        var optional = userAccountRepository.findById(accountId);
        if(!optional.isPresent()) return false;
        return true;
    }

    public static boolean emailExists(String email) {
        var optional = userAccountRepository.findByEmail(email);
        if(!optional.isPresent()) return false;
        return true;
    }

    public static boolean usernameExists(String username) {
        var optional = userAccountRepository.findByUsername(username);
        if(!optional.isPresent()) return false;
        return true;
    }

    public static UserAccountEntity toEntity(AccountRequest request) {
        var entity = new UserAccountEntity();
        entity.setEmail(request.getEmail());
        entity.setPassword(request.getPassword());
        entity.setUsername(request.getUsername());
        entity.setCreatedAt(LocalDateTime.now());

        return entity;
    }
}
