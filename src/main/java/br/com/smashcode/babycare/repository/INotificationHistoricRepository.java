package br.com.smashcode.babycare.repository;

import br.com.smashcode.babycare.model.NotificationHistoricEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface INotificationHistoricRepository extends JpaRepository<NotificationHistoricEntity, UUID> {
}
