package com.javabeanscafe.scripts;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.javabeanscafe.domain.model.Cafe;
import com.javabeanscafe.domain.repository.CafeRepository;
import com.javabeanscafe.infrastructure.enums.TamanhoCafe;
import com.javabeanscafe.infrastructure.enums.TipoCafe;

import lombok.Data;

@Component
@Data
public class CafePopulateScript implements CommandLineRunner {

    private final CafeRepository cafeRepository;

    private void scriptCafe() {
        Set<String> combinacoesUnicas = new HashSet<>();
        List<Cafe> cafes = new ArrayList<>();
        Map<String, BigDecimal> valores = new HashMap<>();
        valores.put("ESPRESSO_PEQUENO", new BigDecimal("3.50"));
        valores.put("ESPRESSO_MEDIO", new BigDecimal("4.00"));
        valores.put("ESPRESSO_GRANDE", new BigDecimal("4.50"));
        valores.put("CAPPUCCINO_PEQUENO", new BigDecimal("4.00"));
        valores.put("CAPPUCCINO_MEDIO", new BigDecimal("4.50"));
        valores.put("CAPPUCCINO_GRANDE", new BigDecimal("5.00"));
        valores.put("LATTE_PEQUENO", new BigDecimal("4.00"));
        valores.put("LATTE_MEDIO", new BigDecimal("4.50"));
        valores.put("LATTE_GRANDE", new BigDecimal("5.00"));
        valores.put("AMERICANO_PEQUENO", new BigDecimal("3.00"));
        valores.put("AMERICANO_MEDIO", new BigDecimal("3.50"));
        valores.put("AMERICANO_GRANDE", new BigDecimal("4.00"));
        valores.put("MACCHIATO_PEQUENO", new BigDecimal("3.50"));
        valores.put("MACCHIATO_MEDIO", new BigDecimal("4.00"));
        valores.put("MACCHIATO_GRANDE", new BigDecimal("4.50"));
        valores.put("MOCHA_PEQUENO", new BigDecimal("4.50"));
        valores.put("MOCHA_MEDIO", new BigDecimal("5.00"));
        valores.put("MOCHA_GRANDE", new BigDecimal("5.50"));
        valores.put("RISTRETTO_PEQUENO", new BigDecimal("3.00"));
        valores.put("RISTRETTO_MEDIO", new BigDecimal("3.50"));
        valores.put("RISTRETTO_GRANDE", new BigDecimal("4.00"));
        valores.put("AFFOGATO_PEQUENO", new BigDecimal("4.50"));
        valores.put("AFFOGATO_MEDIO", new BigDecimal("5.00"));
        valores.put("AFFOGATO_GRANDE", new BigDecimal("5.50"));
        valores.put("IRISH_PEQUENO", new BigDecimal("5.00"));
        valores.put("IRISH_MEDIO", new BigDecimal("5.50"));
        valores.put("IRISH_GRANDE", new BigDecimal("6.00"));
        valores.put("FRAPPE_PEQUENO", new BigDecimal("5.50"));
        valores.put("FRAPPE_MEDIO", new BigDecimal("6.00"));
        valores.put("FRAPPE_GRANDE", new BigDecimal("6.50"));
        valores.put("CORTE_PEQUENO", new BigDecimal("4.00"));
        valores.put("CORTE_MEDIO", new BigDecimal("4.50"));
        valores.put("CORTE_GRANDE", new BigDecimal("5.00"));
        valores.put("AU_LAIT_PEQUENO", new BigDecimal("4.00"));
        valores.put("AU_LAIT_MEDIO", new BigDecimal("4.50"));
        valores.put("AU_LAIT_GRANDE", new BigDecimal("5.00"));
        valores.put("VIENNA_PEQUENO", new BigDecimal("4.50"));
        valores.put("VIENNA_MEDIO", new BigDecimal("5.00"));
        valores.put("VIENNA_GRANDE", new BigDecimal("5.50"));

        for (TipoCafe tipoCafe : TipoCafe.values()) {
            for (TamanhoCafe tamanhoCafe : TamanhoCafe.values()) {
                String combinacao = tipoCafe.name() + "_" + tamanhoCafe.name();
                if (!combinacoesUnicas.contains(combinacao)) {
                    BigDecimal valor = valores.get(combinacao);
                    cafes.add(new Cafe(tipoCafe, tamanhoCafe,
                            tipoCafe.texto,
                            tipoCafe.texto,
                            valor));
                    combinacoesUnicas.add(combinacao);
                }
            }
        }

        cafeRepository.saveAll(cafes);
    }

    @Override
    public void run(String... args) {
        // scriptCafe();
    }

}