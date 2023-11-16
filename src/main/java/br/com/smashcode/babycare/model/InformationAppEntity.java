package br.com.smashcode.babycare.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "informations_app")
@EqualsAndHashCode(of = {"codInfo"})
public class InformationAppEntity implements Serializable {
    @Transient
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID codInfo;

    @Column(length = 30, nullable = false)
    private String infoType;

    @Column(nullable = false)
    @Lob
    private String infoDescription;

    @Column(length = 3, nullable = false)
    private Integer infoVersion;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime timestamp;
}
