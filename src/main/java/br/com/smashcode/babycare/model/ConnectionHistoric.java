package br.com.smashcode.babycare.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table
@EqualsAndHashCode(of = {"codConnection"})
public class ConnectionHistoric implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID codConnection;
    @Column(length = 30, nullable = false)
    private String deviceName;

    @Column(length = 20, nullable = false)
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime connectedAt;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime disconnectedAt;

    /* Relationships */

    @ManyToOne
    @JoinColumn(name = "cod_account")
    private UserAccountEntity account;
}

