package com.javabeanscafe.infrastructure.mail;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javabeanscafe.domain.model.Usuario;

@Service
public class MailService {

    private final static String URL = "http://localhost:4200/";

    @Autowired
    private final MailBuilder mailBuilder;

    @Autowired
    public MailService(MailBuilder mailBuilder) {
        this.mailBuilder = mailBuilder;
    }

    public void sendEmailBoasVindas(Usuario usuario) {
        Map<String, Object> params = new HashMap<>();

        params.put("nome", usuario.getNome());
        params.put("loginLink", URL);

        mailBuilder.to(usuario.getEmail())
                .subject("Welcome to Java Beans Cafe").fire("welcome/body.html", params);
    }
}
