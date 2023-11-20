package br.com.smashcode.babycare.utils;

import br.com.smashcode.babycare.model.BabyProjectEntity;
import br.com.smashcode.babycare.model.UserAccountEntity;
import br.com.smashcode.babycare.repository.IBabyProjectRepository;
import br.com.smashcode.babycare.request.BabyProjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BabyProjectUtils {
    @Autowired
    private static IBabyProjectRepository babyProjectRepository;

    public BabyProjectUtils(IBabyProjectRepository babyProjectRepository) {
        this.babyProjectRepository = babyProjectRepository;
    }


    public static BabyProjectEntity toEntity(BabyProjectRequest request, UserAccountEntity account) {
        var entity = new BabyProjectEntity();
        entity.setBabyName(request.getBabyName());
        if(!request.getGenre().equals(null)) {
            entity.setGenre(request.getGenre());
        }
        entity.setAccount(account);

        return entity;
    }

    public static boolean babyProjectExists(UUID babyProjectId) {
        var optional = babyProjectRepository.findById(babyProjectId);
        if(!optional.isPresent()) {
            return false;
        }
        return true;
    }
}
