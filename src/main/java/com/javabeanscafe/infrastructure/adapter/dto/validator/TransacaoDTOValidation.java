package com.javabeanscafe.infrastructure.adapter.dto.validator;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TransacaoDTOValidation {

    @NotBlank(message = "Valor da transação é obrigatório", groups = TransacaoValidation.ParaTransacao.class)
    private BigDecimal valorTransacao;

    private CartaoDTOValidation cartaoDTOValidation = new CartaoDTOValidation();

    public class TransacaoValidation {

        public interface ParaTransacao {

        }
    }
}
