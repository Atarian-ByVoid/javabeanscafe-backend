package com.javabeanscafe.infrastructure.enums;

public enum TamanhoCafe {

    PEQUENO("Pequeno"),
    MEDIO("Medio"),
    GRANDE("Grande");

    public String texto;

    TamanhoCafe(String tamanhoCafe) {
        texto = tamanhoCafe;
    }

}
