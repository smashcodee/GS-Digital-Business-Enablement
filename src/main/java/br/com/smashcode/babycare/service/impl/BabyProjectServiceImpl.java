package br.com.smashcode.babycare.service.impl;

import br.com.smashcode.babycare.exception.AccountNotExistsException;
import br.com.smashcode.babycare.model.BabyProjectEntity;
import br.com.smashcode.babycare.model.UserAccountEntity;
import br.com.smashcode.babycare.repository.IBabyProjectRepository;
import br.com.smashcode.babycare.repository.IUserAccountRepository;
import br.com.smashcode.babycare.request.BabyProjectRequest;
import br.com.smashcode.babycare.response.BabyProjectResponse;
import br.com.smashcode.babycare.service.IBabyProjectService;
import br.com.smashcode.babycare.utils.BabyProjectUtils;
import br.com.smashcode.babycare.utils.UserAccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BabyProjectServiceImpl implements IBabyProjectService {
    @Autowired
    private IBabyProjectRepository babyProjectRepository;
    @Autowired
    private IUserAccountRepository userAccountRepository;


    @Override
    public BabyProjectResponse createBabyProject(BabyProjectRequest request, UUID accountId) {
        boolean accountExists = UserAccountUtils.userAccountExists(accountId);
        if(!accountExists) {
            throw new AccountNotExistsException("Nenhuma conta foi encontrada com esse id.");
        }
        UserAccountEntity account = userAccountRepository.findById(accountId).get();

        BabyProjectEntity babyProject = BabyProjectUtils.toEntity(request, account);
        var peristed = babyProjectRepository.saveAndFlush(babyProject);

        var response = new BabyProjectResponse(peristed);
        return response;
    }

    @Override
    public BabyProjectResponse updateBabyProject(UUID babyProjectId, BabyProjectRequest request) {
        var accountExists = BabyProjectUtils.babyProjectExists(babyProjectId);
        if(!accountExists) {
            throw new AccountNotExistsException("Nenhuma conta foi encontrada com esse id.");
        }

        var babyProject = babyProjectRepository.findById(babyProjectId).get();
        babyProject.setBabyName(request.getBabyName());
        if(!request.getGenre().equals(null)) {
            babyProject.setGenre(request.getGenre());
        }

        var persisted = babyProjectRepository.saveAndFlush(babyProject);
        var response = new BabyProjectResponse(persisted);
        return response;
    }

    @Override
    public List<BabyProjectResponse> listAllAccountsBabyProject(UUID accountId) {
        boolean accountExists = UserAccountUtils.userAccountExists(accountId);
        if(!accountExists) {
            throw new AccountNotExistsException("Nenhuma conta foi encontrada com esse id.");
        }

        var account = userAccountRepository.findById(accountId).get();
        var list = babyProjectRepository.findAllByAccount(account)
                .stream().map(BabyProjectResponse::new).toList();
        return list;
    }

    /* ADMIN Role */
    @Override
    public List<BabyProjectResponse> listAllBabyProjects() {
        return babyProjectRepository.findAll()
                .stream().map(BabyProjectResponse::new)
                .toList();
    }

    @Override
    public void deleteBabyProject(UUID babyProjectId) {
        var accountExists = BabyProjectUtils.babyProjectExists(babyProjectId);
        if(!accountExists) {
            throw new AccountNotExistsException("Nenhuma conta foi encontrada com esse id.");
        }

        var babyProject = babyProjectRepository.findById(babyProjectId).get();
        babyProjectRepository.delete(babyProject);
    }
}
