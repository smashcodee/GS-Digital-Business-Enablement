package br.com.smashcode.babycare.repository;

import br.com.smashcode.babycare.model.ConnectionHistoric;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IConnectionHistoricRepository extends JpaRepository<ConnectionHistoric, UUID> {
}
