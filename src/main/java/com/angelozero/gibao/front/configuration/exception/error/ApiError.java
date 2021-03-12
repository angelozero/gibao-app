package com.angelozero.gibao.front.configuration.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ApiError {

    private final String menssagem;
    private final HttpStatus status;
    private final Object identificador;
}