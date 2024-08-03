package com.javabeanscafe.infrastructure.adapter.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javabeanscafe.domain.model.Endereco;
import com.javabeanscafe.domain.repository.EnderecoRepository;
import com.javabeanscafe.infrastructure.adapter.persistence.interfaces.EnderecoJpaRepository;

@Repository
public class JpaEnderecoRepository implements EnderecoRepository {

    private final EnderecoJpaRepository enderecoJpaRepository;

    @Autowired
    public JpaEnderecoRepository(EnderecoJpaRepository enderecoJpaRepository) {
        this.enderecoJpaRepository = enderecoJpaRepository;
    }

    @Override
    public Endereco salvar(Endereco endereco) {
        return enderecoJpaRepository.save(endereco);
    }

}
