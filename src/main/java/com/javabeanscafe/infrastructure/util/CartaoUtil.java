package com.javabeanscafe.infrastructure.util;

import java.util.Random;

public class CartaoUtil {

    public static String gerarNumeroCartao() {
        Random random = new Random();
        StringBuilder numeroCartao = new StringBuilder();

        int prefixo = 4;
        numeroCartao.append(prefixo);

        for (int i = 0; i < 15; i++) {
            numeroCartao.append(random.nextInt(10));
        }

        int digitoVerificacao = calcularDigitoVerificacao(numeroCartao.toString());
        numeroCartao.append(digitoVerificacao);

        return numeroCartao.toString();
    }

    private static int calcularDigitoVerificacao(String numeroParcial) {
        int soma = 0;
        boolean alternar = false;

        for (int i = numeroParcial.length() - 1; i >= 0; i--) {
            int digito = Integer.parseInt(numeroParcial.substring(i, i + 1));

            if (alternar) {
                digito *= 2;
                if (digito > 9) {
                    digito -= 9;
                }
            }

            soma += digito;
            alternar = !alternar;
        }

        int digitoVerificacao = (10 - (soma % 10)) % 10;
        return digitoVerificacao;
    }

    public static String gerarCVV() {
        Random random = new Random();
        int cvv = 100 + random.nextInt(900);
        return String.valueOf(cvv);
    }

}
