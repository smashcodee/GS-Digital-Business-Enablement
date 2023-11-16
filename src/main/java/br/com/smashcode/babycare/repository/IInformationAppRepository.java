package br.com.smashcode.babycare.repository;

import br.com.smashcode.babycare.model.InformationAppEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IInformationAppRepository extends JpaRepository<InformationAppEntity, UUID> {
}
