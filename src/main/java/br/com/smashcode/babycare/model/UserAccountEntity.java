package br.com.smashcode.babycare.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "user_accounts")
@EqualsAndHashCode(of = {"codAccount", "email", "username"})
public class UserAccountEntity implements Serializable {
    @Transient
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID codAccount;

    @Column(unique = true, nullable = false, length = 80)
    private String email;

    @Column(nullable = false, length = 120)
    private String password;

    @Column(unique = true, nullable = false, length = 80)
    private String username;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    /* Relationships */

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UserProfileEntity profile;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ConnectionHistoric> connectionHistorics;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<NotificationHistoricEntity> notificationHistorics;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<BabyProjectEntity> babyProjects;
}
