package com.javabeanscafe.infrastructure.adapter.error;

import com.javabeanscafe.application.exception.CustomException;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ErrorDTO {

    int statusCode;
    Object message;
    String error;

    public ErrorDTO(CustomException e) {
        this.statusCode = e.getStatusCode().value();
        this.message = e.getMessage();
        this.error = e.getError();
    }

}
