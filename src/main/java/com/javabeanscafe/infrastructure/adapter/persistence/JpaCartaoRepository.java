package com.javabeanscafe.infrastructure.adapter.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javabeanscafe.domain.model.Cartao;
import com.javabeanscafe.domain.repository.CartaoRepository;
import com.javabeanscafe.infrastructure.adapter.persistence.interfaces.CartaoJpaRepository;

@Repository
public class JpaCartaoRepository implements CartaoRepository {

    private final CartaoJpaRepository cartaoJpaRepository;

    @Autowired
    public JpaCartaoRepository(CartaoJpaRepository cartaoJpaRepository) {
        this.cartaoJpaRepository = cartaoJpaRepository;
    }

    @Override
    public void removerCartao(Cartao cartao) {
        this.cartaoJpaRepository.save(cartao);
    }

}
