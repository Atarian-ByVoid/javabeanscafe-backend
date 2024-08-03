package com.javabeanscafe.application.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.javabeanscafe.application.exception.CustomException;
import com.javabeanscafe.domain.mapper.CartaoMapper;
import com.javabeanscafe.domain.model.Cartao;
import com.javabeanscafe.domain.model.Usuario;
import com.javabeanscafe.domain.repository.CartaoRepository;
import com.javabeanscafe.domain.repository.UsuarioRepository;
import com.javabeanscafe.infrastructure.adapter.authentication.JwtService;
import com.javabeanscafe.infrastructure.adapter.client.CGIHttpClient;
import com.javabeanscafe.infrastructure.adapter.dto.CartaoDTO;
import com.javabeanscafe.infrastructure.adapter.dto.validator.CartaoDTOValidation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.Data;

@Service
@Data
public class CartaoService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final CartaoMapper cartaoMapper;
    private final CartaoRepository cartaoRepository;
    private final CGIHttpClient cgiHttpClient;

    @Transactional
    public CartaoDTO salvarCartaoPagamento(
            HttpServletRequest token,
            CartaoDTOValidation request) {

        String authToken = jwtService.extractToken(token);
        String userId = jwtService.getUserIdFromToken(authToken);
        UUID accountId = UUID.fromString(userId);

        Usuario usuario = usuarioRepository.findById(accountId);

        if (usuario.getCartao() != null) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                    "O usuário já possui um cartão", "Bad Request");
        }

        this.cgiHttpClient.validarCartaoCGI(request).block();

        Cartao cartao = new Cartao();
        cartao.setBandeira("CGI");
        cartao.setCvv(request.getCvv());
        cartao.setDataValidade(request.getDataValidade());
        cartao.setNumero(request.getNumero());
        cartao.setTitular(request.getTitular());
        cartao.setTipoCartao(request.getTipoCartao());
        cartao.setUsuario(usuario);

        usuario.setCartao(cartao);

        usuarioRepository.salvar(usuario);

        return cartaoMapper.convertToCartaoDTO(cartao);

    }

    public CartaoDTO findById(HttpServletRequest token) {

        String authToken = jwtService.extractToken(token);

        String userId = jwtService.getUserIdFromToken(authToken);
        UUID accountId = UUID.fromString(userId);

        Usuario usuario = usuarioRepository.findById(accountId);

        return cartaoMapper.convertToCartaoDTO(usuario.getCartao());

    }

    public void removerCartao(HttpServletRequest token) {

        String authToken = jwtService.extractToken(token);

        String userId = jwtService.getUserIdFromToken(authToken);
        UUID accountId = UUID.fromString(userId);

        Usuario usuario = usuarioRepository.findById(accountId);

        Cartao cartao = usuario.getCartao();

        if (cartao.getDeletadoEm() == null) {
            cartao.setDeletadoEm(LocalDateTime.now());

            cartaoRepository.removerCartao(cartao);
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND,
                    "Cartão já removido ou não encontrado", "Not Found");
        }

    }

}
