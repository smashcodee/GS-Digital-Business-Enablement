package br.com.smashcode.babycare.repository;

import br.com.smashcode.babycare.model.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUserAccountRepository extends JpaRepository<UserAccountEntity, UUID> {
}
