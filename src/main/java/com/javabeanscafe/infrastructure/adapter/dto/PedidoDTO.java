package com.javabeanscafe.infrastructure.adapter.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.javabeanscafe.infrastructure.adapter.dto.CafeDTO.CafeViews;
import com.javabeanscafe.infrastructure.adapter.dto.EnderecoDTO.EnderecoViews;
import com.javabeanscafe.infrastructure.adapter.dto.ItemPedidoDTO.ItemPedidoViews;
import com.javabeanscafe.infrastructure.adapter.dto.UsuarioDTO.UsuarioViews;
import com.javabeanscafe.infrastructure.adapter.dto.others.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PedidoDTO extends AbstractDTO {

    @JsonView({ PedidoDTOViews.DetalhadoParaPedido.class })
    private UsuarioDTO usuario;

    @JsonView({ PedidoDTOViews.DetalhadoParaPedido.class })
    private List<ItemPedidoDTO> itemPedido;

    @JsonView({ PedidoDTOViews.DetalhadoParaPedido.class })
    private BigDecimal valorDaTransacao;

    public class PedidoDTOViews {
        public interface DetalhadoParaPedido extends ItemPedidoViews.DetalhadoCafeParaPedido, CafeViews.Get,
                UsuarioViews.ParaPedido, EnderecoViews.Send {

        }

    }

}
