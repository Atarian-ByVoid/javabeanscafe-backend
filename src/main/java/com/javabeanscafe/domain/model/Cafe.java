package com.javabeanscafe.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.javabeanscafe.domain.others.AbstractEntity;
import com.javabeanscafe.infrastructure.enums.TamanhoCafe;
import com.javabeanscafe.infrastructure.enums.TipoCafe;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cafe")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Cafe extends AbstractEntity {

    @Column(nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    private TipoCafe tipoCafe;

    @Column(nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    private TamanhoCafe tamanhoCafe;

    @Column(nullable = false, unique = false)
    private String descricao;

    @Column(nullable = false, unique = false)
    private String nome;

    @Column(nullable = false, unique = false)
    private BigDecimal valor;

    @OneToMany(mappedBy = "cafe", cascade = CascadeType.ALL)
    private List<ItemPedido> itensPedidos = new ArrayList<>();

    public Cafe(TipoCafe tipoCafe, TamanhoCafe tamanhoCafe, String descricao, String nome, BigDecimal valor) {
        this.tipoCafe = tipoCafe;
        this.tamanhoCafe = tamanhoCafe;
        this.descricao = descricao;
        this.nome = nome;
        this.valor = valor;
    }

}