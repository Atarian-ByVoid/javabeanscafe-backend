package com.javabeanscafe.infrastructure.adapter.entrypoint;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.javabeanscafe.application.service.PedidoService;
import com.javabeanscafe.infrastructure.adapter.dto.PedidoDTO;
import com.javabeanscafe.infrastructure.adapter.dto.PedidoDTO.PedidoDTOViews;
import com.javabeanscafe.infrastructure.adapter.dto.validator.ItemPedidoDTOValidation;
import com.javabeanscafe.infrastructure.adapter.dto.validator.ItemPedidoDTOValidation.ItemPedidoValidation;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping()
    @JsonView(PedidoDTOViews.DetalhadoParaPedido.class)
    public ResponseEntity<PedidoDTO> adicionarItensAoPedido(
            @Validated({ ItemPedidoValidation.Create.class }) @RequestBody List<ItemPedidoDTOValidation> itens,
            HttpServletRequest token) {

        PedidoDTO pedidoDTO = pedidoService.adicionarItensAoPedido(itens, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoDTO);

    }

}
