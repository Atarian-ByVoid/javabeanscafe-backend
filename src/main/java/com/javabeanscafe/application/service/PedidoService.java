package com.javabeanscafe.application.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javabeanscafe.domain.mapper.PedidoMapper;
import com.javabeanscafe.domain.model.Cafe;
import com.javabeanscafe.domain.model.ItemPedido;
import com.javabeanscafe.domain.model.Pedido;
import com.javabeanscafe.domain.model.Usuario;
import com.javabeanscafe.domain.repository.PedidoRepository;
import com.javabeanscafe.domain.repository.UsuarioRepository;
import com.javabeanscafe.infrastructure.adapter.authentication.JwtService;
import com.javabeanscafe.infrastructure.adapter.client.CGIHttpClient;
import com.javabeanscafe.infrastructure.adapter.dto.CartaoDTO;
import com.javabeanscafe.infrastructure.adapter.dto.PedidoDTO;
import com.javabeanscafe.infrastructure.adapter.dto.TransacaoDTO;
import com.javabeanscafe.infrastructure.adapter.dto.validator.ItemPedidoDTOValidation;
import com.javabeanscafe.infrastructure.adapter.producer.PagamentoRequestProducer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.Data;

@Service
@Data
public class PedidoService {

    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final CafeService cafeService;
    private final PedidoRepository pedidoRepository;
    private final CGIHttpClient cgiHttpClient;
    private final CartaoService cartaoService;
    private final PedidoMapper pedidoMapper;
    private final PagamentoRequestProducer pagamentoRequestProducer;

    @Transactional
    public PedidoDTO adicionarItensAoPedido(List<ItemPedidoDTOValidation> itemPedidoDTOs, HttpServletRequest token) {

        String authToken = jwtService.extractToken(token);

        String userId = jwtService.getUserIdFromToken(authToken);
        UUID accountId = UUID.fromString(userId);

        Usuario usuario = usuarioRepository.findById(accountId);

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);

        BigDecimal precoTotal = BigDecimal.ZERO;
        for (ItemPedidoDTOValidation itemPedidoDTO : itemPedidoDTOs) {
            Cafe cafe = cafeService.findByIdEntidade(itemPedidoDTO.getCafeId(), token);

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedido);
            itemPedido.setCafe(cafe);
            itemPedido.setQuantidade(itemPedidoDTO.getQuantidade());

            precoTotal = precoTotal.add(cafe.getValor().multiply(BigDecimal.valueOf(itemPedidoDTO.getQuantidade())));

            pedido.getItens().add(itemPedido);
        }

        // TransacaoDTOValidation transacaoDTOValidation = new TransacaoDTOValidation();
        // transacaoDTOValidation.setValorTransacao(precoTotal);

        // CartaoDTO cartaoDTO = cartaoService.findById(token);
        // CartaoDTOValidation cartaoDTOValidation = new CartaoDTOValidation();
        // cartaoDTOValidation.setCvv(cartaoDTO.getCvv());
        // cartaoDTOValidation.setDataValidade(cartaoDTO.getDataValidade());
        // cartaoDTOValidation.setDocumento(usuario.getCpf());
        // cartaoDTOValidation.setTipoCartao(cartaoDTO.getTipoCartao());
        // cartaoDTOValidation.setTitular(cartaoDTO.getTitular());
        // transacaoDTOValidation.setCartaoDTOValidation(cartaoDTOValidation);

        CartaoDTO cartaoDTO = cartaoService.findById(token);

        TransacaoDTO transacaoDTO = new TransacaoDTO();
        transacaoDTO.setValorTransacao(precoTotal);
        transacaoDTO.setCartaoDTO(cartaoDTO);

        try {
            pagamentoRequestProducer.sendMessage(transacaoDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // cgiHttpClient.requisitarTransacao(transacaoDTOValidation).block();

        pedidoRepository.save(pedido);

        return pedidoMapper.convertToPedidoDTO(pedido, precoTotal);

    }

}
