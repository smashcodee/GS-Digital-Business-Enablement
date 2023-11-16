package br.com.smashcode.babycare.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Entity
@Data
@Table(name = "user_profiles")
@EqualsAndHashCode(of = {"codUserProfile"})
public class UserProfileEntity {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID codUserProfile;
    @Column(nullable = false, length = 3)
    private Integer age;

    @Column(nullable = false, length = 80)
    private String fullName;

    @Column(nullable = false, length = 5)
    private String bloodType;

    /* Relationships */

    @OneToOne
    @JoinColumn(name = "cod_account")
    private UserAccountEntity account;
}
