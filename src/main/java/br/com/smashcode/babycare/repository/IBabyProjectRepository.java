package br.com.smashcode.babycare.repository;

import br.com.smashcode.babycare.model.BabyProjectEntity;
import br.com.smashcode.babycare.model.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IBabyProjectRepository extends JpaRepository<BabyProjectEntity, UUID> {
    List<BabyProjectEntity> findAllByAccount(UserAccountEntity account);
}
