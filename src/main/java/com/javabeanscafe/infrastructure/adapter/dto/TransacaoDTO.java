package com.javabeanscafe.infrastructure.adapter.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TransacaoDTO {

    private BigDecimal valorTransacao;

    private CartaoDTO cartaoDTO;

}
