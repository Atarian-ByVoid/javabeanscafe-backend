package com.javabeanscafe.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.javabeanscafe.domain.others.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "endereco")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Endereco extends AbstractEntity {

    @Column(length = 255, nullable = false)
    private String logradouro;

    @Column(length = 100, nullable = false)
    private String bairro;

    @Column(length = 100, nullable = false)
    private String cidade;

    @Column(length = 5, nullable = false)
    private String uf;

    @Column(length = 5, nullable = false)
    private String numero;

    @Column(columnDefinition = "VARCHAR(50) NOT NULL DEFAULT 'Brasil'")
    private String pais;

    @Column(length = 15, nullable = false)
    private String cep;

    @Column(length = 100, nullable = false)
    private String complemento;

    @JsonIgnore
    @OneToOne(mappedBy = "endereco", fetch = FetchType.LAZY)
    private Usuario usuario;

}
