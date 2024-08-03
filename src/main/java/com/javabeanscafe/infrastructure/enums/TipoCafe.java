package com.javabeanscafe.infrastructure.enums;

public enum TipoCafe {

    ESPRESSO("Espresso"),
    CAPPUCCINO("Cappuccino"),
    LATTE("Latte"),
    AMERICANO("Americano"),
    MACCHIATO("Macchiato"),
    MOCHA("Mocha"),
    RISTRETTO("Ristretto"),
    AFFOGATO("Affogato"),
    IRISH("Irish"),
    FRAPPE("Frappe"),
    CORTE("Corte"),
    AU_LAIT("Au Lait"),
    VIENNA("Vienna");

    public String texto;

    TipoCafe(String tipoCafe) {
        texto = tipoCafe;
    }

}
