package com.javabeanscafe.domain.model;

import java.time.LocalDate;

import com.javabeanscafe.domain.others.AbstractEntity;
import com.javabeanscafe.infrastructure.enums.TipoCartao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cartao")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Cartao extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String numero;

    @Column(nullable = false, unique = true)
    private String titular;

    @Column(nullable = false, unique = true)
    private LocalDate dataValidade;

    @Column(nullable = false, unique = true)
    private String cvv;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private TipoCartao tipoCartao;

    @Column(nullable = false, unique = true)
    private String bandeira;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

}
