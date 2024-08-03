package com.javabeanscafe.infrastructure.adapter.persistence;

import org.springframework.stereotype.Repository;

import com.javabeanscafe.domain.model.Pedido;
import com.javabeanscafe.domain.repository.PedidoRepository;
import com.javabeanscafe.infrastructure.adapter.persistence.interfaces.PedidoJpaRepository;

@Repository
public class JpaPedidoRepository implements PedidoRepository {
    private final PedidoJpaRepository pedidoJpaRepository;

    public JpaPedidoRepository(PedidoJpaRepository pedidoJpaRepository) {
        this.pedidoJpaRepository = pedidoJpaRepository;
    }

    @Override
    public Pedido save(Pedido pedido) {
        return this.pedidoJpaRepository.save(pedido);
    }

}
