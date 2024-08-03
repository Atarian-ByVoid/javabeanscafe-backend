package com.javabeanscafe.infrastructure.adapter.entrypoint;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.javabeanscafe.application.service.CartaoService;
import com.javabeanscafe.infrastructure.adapter.dto.CartaoDTO;
import com.javabeanscafe.infrastructure.adapter.dto.CartaoDTO.CartaoViews;
import com.javabeanscafe.infrastructure.adapter.dto.validator.CartaoDTOValidation;
import com.javabeanscafe.infrastructure.adapter.dto.validator.CartaoDTOValidation.CartaoValidation;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "cartao")
@AllArgsConstructor
public class CartaoController {

    private final CartaoService cartaoService;

    @PostMapping
    @JsonView(CartaoViews.Send.class)
    public ResponseEntity<CartaoDTO> criarCartao(
            HttpServletRequest token,
            @Validated({ CartaoValidation.ValidarPorAPI.class }) @RequestBody CartaoDTOValidation request) {

        CartaoDTO cartaoDTO = cartaoService.salvarCartaoPagamento(token, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(cartaoDTO);
    }

    @GetMapping()
    @JsonView(CartaoViews.Get.class)
    public ResponseEntity<CartaoDTO> buscarUsuario(
            HttpServletRequest token) {

        CartaoDTO cartaoDTO = cartaoService.findById(token);

        return ResponseEntity.ok(cartaoDTO);
    }

    @DeleteMapping()
    public ResponseEntity delete(HttpServletRequest token) {
        cartaoService.removerCartao(token);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Cartão removido com sucesso");

        return ResponseEntity.ok(response);
    }

}
