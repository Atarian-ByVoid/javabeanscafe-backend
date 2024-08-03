package com.javabeanscafe.infrastructure.adapter.client;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.javabeanscafe.application.exception.CustomException;
import com.javabeanscafe.infrastructure.adapter.dto.validator.CartaoDTOValidation;
import com.javabeanscafe.infrastructure.adapter.dto.validator.TransacaoDTOValidation;
import com.javabeanscafe.infrastructure.adapter.error.ErrorDTO;

import reactor.core.publisher.Mono;

@Service
public class CGIHttpClient {

        private WebClient webClient;
        private String baseUrl;

        public CGIHttpClient() {
                this.baseUrl = "http://localhost:8081/validacao";
                this.webClient = WebClient.create(baseUrl);
        }

        public Mono<String> validarCartaoCGI(CartaoDTOValidation cartaoDTOValidation) {
                return webClient
                                .post()
                                .uri("/validar-cartao")
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromValue(cartaoDTOValidation))
                                .retrieve()
                                .onStatus(HttpStatusCode::isError, response -> response.bodyToMono(ErrorDTO.class)
                                                .flatMap(error -> Mono.error(new CustomException(error))))
                                .bodyToMono(String.class);
        }

        public Mono<String> requisitarTransacao(TransacaoDTOValidation transacaoDTOValidation) {
                return webClient
                                .post()
                                .uri("/requisitar-transacao")
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromValue(
                                                transacaoDTOValidation))
                                .retrieve()
                                .onStatus(HttpStatusCode::isError, response -> response.bodyToMono(ErrorDTO.class)
                                                .flatMap(error -> Mono.error(new CustomException(error))))
                                .bodyToMono(String.class);
        }
}
