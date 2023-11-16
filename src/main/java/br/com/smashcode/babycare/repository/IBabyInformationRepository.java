package br.com.smashcode.babycare.repository;

import br.com.smashcode.babycare.model.BabyInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IBabyInformationRepository extends JpaRepository<BabyInformationEntity, UUID> {
}
