package com.javabeanscafe.infrastructure.adapter.dto.validator;

import java.time.LocalDate;

import com.javabeanscafe.infrastructure.enums.TipoCartao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartaoDTOValidation {

    @NotNull(message = "Numero do cartão é obrigatório", groups = CartaoValidation.ValidarPorAPI.class)
    private String numero;

    @NotNull(message = "Titular do cartão é obrigatório", groups = CartaoValidation.ValidarPorAPI.class)
    private String titular;

    @NotNull(message = "Data de validade do cartão é obrigatório", groups = CartaoValidation.ValidarPorAPI.class)
    private LocalDate dataValidade;

    @NotNull(message = "CVV do cartão é obrigatório", groups = CartaoValidation.ValidarPorAPI.class)
    private String cvv;

    @NotNull(message = "Tipo de cartão é obrigatório", groups = CartaoValidation.ValidarPorAPI.class)
    private TipoCartao tipoCartao;

    @NotBlank(message = "Documento do titular é obrigatório", groups = CartaoValidation.ValidarPorAPI.class)
    private String documento;

    public class CartaoValidation {

        public interface ValidarPorAPI {

        }
    }

}
