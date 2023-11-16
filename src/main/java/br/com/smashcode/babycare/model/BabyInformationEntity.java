package br.com.smashcode.babycare.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "baby_informations")
@EqualsAndHashCode(of = {"codBabyInfo"})
public class BabyInformationEntity implements Serializable {
    @Transient
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID codBabyInfo;

    @Column(length = 3, precision = 2, nullable = false)
    private Double heartRate;

    @Column(length = 3, precision = 2, nullable = false)
    private Double oxygenRate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime timestamp;

    /* Relationships */

    @OneToOne
    @JoinColumn(name = "cod_baby_info")
    private BabyProjectEntity babyProject;
}
