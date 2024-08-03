package com.javabeanscafe.domain.others;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "criado_em", nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime criadoEm;

    @Column(name = "alterado_em", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime alteradoEm;

    @Column(name = "deletado_em", nullable = true, columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT NULL")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime deletadoEm;

    @PrePersist
    public void prePersistTimestamp() {
        criadoEm = LocalDateTime.now();
        alteradoEm = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdateTimestamp() {
        alteradoEm = LocalDateTime.now();
    }

}
