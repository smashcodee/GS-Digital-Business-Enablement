package br.com.smashcode.babycare.service.impl;

import br.com.smashcode.babycare.exception.AccountNotExistsException;
import br.com.smashcode.babycare.exception.EmailAlreadyExistsException;
import br.com.smashcode.babycare.exception.UsernameAlreadyExistsException;
import br.com.smashcode.babycare.model.UserAccountEntity;
import br.com.smashcode.babycare.model.UserProfileEntity;
import br.com.smashcode.babycare.repository.IUserAccountRepository;
import br.com.smashcode.babycare.repository.IUserProfileRepository;
import br.com.smashcode.babycare.request.AccountRequest;
import br.com.smashcode.babycare.response.AccountResponse;
import br.com.smashcode.babycare.response.SuccessResponse;
import br.com.smashcode.babycare.service.IAccountService;
import br.com.smashcode.babycare.service.IEmailService;
import br.com.smashcode.babycare.utils.UserAccountUtils;
import br.com.smashcode.babycare.utils.UserProfileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IUserAccountRepository userAccountRepository;

    @Autowired
    private IUserProfileRepository userProfileRepository;

    @Autowired
    private IEmailService emailService;


    @Override
    public AccountResponse createAccount(AccountRequest request) {
        boolean emailNotExists = UserAccountUtils.emailExists(request.getEmail());
        boolean usernameNotExists = UserAccountUtils.usernameExists(request.getEmail());

        if(emailNotExists) throw new EmailAlreadyExistsException("O email informado já foi cadastrado por alguém.");
        if(usernameNotExists) throw new UsernameAlreadyExistsException("O username informado já esta em uso.");

        /* Se chegou até aqui, tudo certo. */

        /* Criar um user_account */
        UserAccountEntity accountEntity = UserAccountUtils.toEntity(request);
        // salvando conta de usuário no banco de dados.
        var accountPersisted = userAccountRepository.saveAndFlush(accountEntity);

        /* Criar um user_profile */
        UserProfileEntity profileEntity = UserProfileUtils.toEntity(request, accountPersisted);
        // salvando perfil de usuário no banco de dados.
        var profilePersisted = userProfileRepository.saveAndFlush(profileEntity);

        // retornar um dto com os dados.
        var response = new AccountResponse(profilePersisted);

        // enviar um email de agradecimentos
        var send = emailService.sendEmail(
                response.getEmail(),
                "Obrigado por acreditar em nós!",
                "A equipe da Baby Care agradece profundamente por você ter confiado no nosso serviço!"
        );

        return response;
    }

    /**
     * Admin Role
     * */
    @Override
    public List<AccountResponse> listAccounts() {
        var list = userProfileRepository.findAll().stream().map(AccountResponse::new).toList();
        return list;
    }

    @Override
    public SuccessResponse updateEmail(UUID accountId, String email) {
        // para atualizar o email é necessário validar a senha do usuário.
        // fazer isso depois.
        boolean accountNotExists = UserAccountUtils.userAccountExists(accountId);
        boolean emailExists = UserAccountUtils.emailExists(email);

        if(!accountNotExists) throw new AccountNotExistsException("Nenhuma conta foi encontrado com o id informado.");
        if(emailExists) throw new EmailAlreadyExistsException("O email informado já foi cadastrado por alguém.");

        /* Se chegou até aqui, tudo certo. */
        var accountEntity = userAccountRepository.findById(accountId).get();
        accountEntity.setEmail(email);

        // editando entidade da conta
        var accountUpdated = userAccountRepository.saveAndFlush(accountEntity);
        return new SuccessResponse("Email editado com sucesso.");
    }

    @Override
    public SuccessResponse updatePassword(UUID accountId, String password) {
        // Regra de negócio: mandar um código de verificação pelo email.
        // fazer isso depois.

        // depois criptografar a senha
        boolean accountExists = UserAccountUtils.userAccountExists(accountId);
        if(!accountExists) throw new AccountNotExistsException("Nenhuma conta foi encontrado com o id informado.");

        /* Se chegou até aqui, tudo certo. */
        var accountEntity = userAccountRepository.findById(accountId).get();
        accountEntity.setPassword(password);

        return new SuccessResponse("Senha alterada com sucesso");
    }

    @Override
    public SuccessResponse updateUsername(UUID accountId, String username) {
        // para atualizar o email é necessário validar a senha do usuário.
        // fazer isso depois.

        boolean accountExists = UserAccountUtils.userAccountExists(accountId);
        boolean usernameExists = UserAccountUtils.usernameExists(username);

        if(!accountExists) throw new AccountNotExistsException("Nenhuma conta foi encontrado com o id informado.");
        if(usernameExists) throw new UsernameAlreadyExistsException("O username informado já esta em uso.");


        /* Se chegou até aqui, tudo certo. */
        var accountEntity = userAccountRepository.findById(accountId).get();
        accountEntity.setUsername(username);

        var accountUpdated = userAccountRepository.saveAndFlush(accountEntity);

        return new SuccessResponse("Username alterado com sucesso.");
    }

    @Override
    public SuccessResponse updateFullName(UUID accountId, String fullName) {
        // Aqui não tem muita regra de negócio, só algumas validações simples .-.

        boolean accountExists = UserAccountUtils.userAccountExists(accountId);

        if(!accountExists) throw new AccountNotExistsException("Nenhuma conta foi encontrado com o id informado.");


        /* Se chegou até aqui, tudo certo. */
        var accountEntity = userAccountRepository.findById(accountId).get();
        var profile = accountEntity.getProfile();
        profile.setFullName(fullName);

        var profileUpdated = userProfileRepository.saveAndFlush(profile);

        return new SuccessResponse("Nome do usuário alterado com sucesso.");
    }

    @Override
    public SuccessResponse deleteAccount(UUID accountId) {
        boolean accountNotExists = UserAccountUtils.userAccountExists(accountId);

        if(accountNotExists) throw new AccountNotExistsException("Nenhuma conta foi encontrado com o id informado.");

        /* Se chegou até aqui, tudo certo. */
        var accountEntity = userAccountRepository.findById(accountId).get();
        userAccountRepository.delete(accountEntity);

        return new SuccessResponse("Conta deletada com sucesso.");
    }
}
