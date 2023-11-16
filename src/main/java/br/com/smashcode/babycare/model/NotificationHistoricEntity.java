package br.com.smashcode.babycare.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "notifications_historic")
@EqualsAndHashCode(of = {"codNotification"})
public class NotificationHistoricEntity implements Serializable {
    @Transient
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID codNotification;

    @Column(length = 100, nullable = false)
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime sentAt;

    @Column(length = 15, nullable = false)
    private String readStatus;

    /* Relationships */

    @ManyToOne
    @JoinColumn(name = "cod_account")
    private UserAccountEntity account;
}
