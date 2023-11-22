package br.com.smashcode.babycare.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Data
@Table(name = "user_accounts")
@EqualsAndHashCode(of = {"codAccount", "email", "username"})
public class UserAccountEntity implements UserDetails {
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



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
          return List.of(
            new SimpleGrantedAuthority("ROLE_USER")
        );
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
