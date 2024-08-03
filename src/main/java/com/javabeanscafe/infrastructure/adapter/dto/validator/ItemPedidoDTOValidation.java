package com.javabeanscafe.infrastructure.adapter.dto.validator;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ItemPedidoDTOValidation {

    @NotBlank(message = "Id do café é obrigatório", groups = ItemPedidoValidation.Create.class)
    private String cafeId;

    @NotBlank(message = "Quantidade do café é obrigatório", groups = ItemPedidoValidation.Create.class)
    private int quantidade;

    public class ItemPedidoValidation {

        public interface Create {

        }

    }

}
