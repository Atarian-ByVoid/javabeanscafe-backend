package com.javabeanscafe.infrastructure.enums;

public enum Genero {
    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    OUTRO("Outro"),
    NAO_INFORMAR("Não Informar");

    public String texto;

    Genero(String genero) {
        texto = genero;
    }
}
