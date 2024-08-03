package com.javabeanscafe.infrastructure.adapter.persistence.interfaces;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javabeanscafe.domain.model.Endereco;

public interface EnderecoJpaRepository extends JpaRepository<Endereco, UUID> {

}
