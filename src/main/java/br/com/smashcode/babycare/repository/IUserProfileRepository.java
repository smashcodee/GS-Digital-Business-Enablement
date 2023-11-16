package br.com.smashcode.babycare.repository;

import br.com.smashcode.babycare.model.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUserProfileRepository extends JpaRepository<UserProfileEntity, UUID> {
}
