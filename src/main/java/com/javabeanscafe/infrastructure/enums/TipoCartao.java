package com.javabeanscafe.infrastructure.enums;

public enum TipoCartao {

    CREDITO("Crédito"),
    DEBITO("Débito");

    public String texto;

    TipoCartao(String tipoCartao) {
        texto = tipoCartao;
    }

}
