package br.com.smashcode.babycare.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Table(name = "baby_projects")
@EqualsAndHashCode(of = {"codBabyProject"})
public class BabyProjectEntity implements Serializable {
    @Transient
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID codBabyProject;

    @Column(length = 80, nullable = false)
    private String babyName;

    @Column(length = 80, nullable = false)
    private String genre;

    /* Relationships */

    @ManyToOne
    @JoinColumn(name = "cod_account")
    private UserAccountEntity account;

    @OneToOne(mappedBy = "babyProject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private BabyInformationEntity babyInformation;
}
