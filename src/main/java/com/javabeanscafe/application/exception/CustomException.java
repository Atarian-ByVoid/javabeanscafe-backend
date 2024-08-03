package com.javabeanscafe.application.exception;

import java.util.ArrayList;

import org.springframework.http.HttpStatusCode;

import com.javabeanscafe.infrastructure.adapter.error.ErrorDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CustomException extends RuntimeException {

    HttpStatusCode statusCode;
    String message;
    String error;

    public CustomException(final ErrorDTO body) {
        this.statusCode = HttpStatusCode.valueOf(body.getStatusCode());
        this.error = body.getError();

        final Object message = body.getMessage();
        this.message = message instanceof String ? (String) message
                : message != null ? String.join(", ", (ArrayList<String>) message)
                        : "Mensagem de erro não especificada.";
    }

    public CustomException(final HttpStatusCode statusCode, final String message, final String error) {
        this.statusCode = statusCode;
        this.message = message;
        this.error = error;
    }

    public CustomException(final HttpStatusCode statusCode, final String error) {
        this.statusCode = statusCode;
        this.error = error;
    }

    public CustomException(final int statusCode, final String message, final String error) {
        this.statusCode = HttpStatusCode.valueOf(statusCode);
        this.message = message;
        this.error = error;
    }

}
