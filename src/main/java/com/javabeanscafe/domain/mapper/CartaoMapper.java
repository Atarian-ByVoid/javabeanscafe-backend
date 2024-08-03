package com.javabeanscafe.domain.mapper;

import org.springframework.stereotype.Component;

import com.javabeanscafe.domain.model.Cartao;
import com.javabeanscafe.infrastructure.adapter.dto.CartaoDTO;

@Component
public class CartaoMapper {

    public CartaoDTO convertToCartaoDTO(Cartao cartao) {

        CartaoDTO cartaoDTO = new CartaoDTO();
        cartaoDTO.setCriadoEm(cartao.getCriadoEm());
        cartaoDTO.setAlteradoEm(cartao.getAlteradoEm());
        cartaoDTO.setDeletadoEm(cartao.getDeletadoEm());
        cartaoDTO.setId(cartao.getId());
        cartaoDTO.setBandeira(cartao.getBandeira());
        cartaoDTO.setCvv(cartao.getCvv());
        cartaoDTO.setDataValidade(cartao.getDataValidade());
        cartaoDTO.setNumero(cartao.getNumero());
        cartaoDTO.setTipoCartao(cartao.getTipoCartao());
        cartaoDTO.setTitular(cartao.getTitular());

        return cartaoDTO;
    }

}
