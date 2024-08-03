package com.javabeanscafe.infrastructure.adapter.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.javabeanscafe.infrastructure.adapter.dto.others.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ItemPedidoDTO extends AbstractDTO {

    @JsonView({ ItemPedidoViews.Send.class })
    private String cafeId;

    @JsonView({ ItemPedidoViews.Send.class, ItemPedidoViews.DetalhadoCafeParaPedido.class })
    private int quantidade;

    @JsonView({ ItemPedidoViews.DetalhadoCafeParaPedido.class })
    private CafeDTO cafe = new CafeDTO();

    public class ItemPedidoViews {
        public interface Send {

        }

        public interface DetalhadoCafeParaPedido {

        }

        public interface Detalhado extends Send {

        }
    }
}
