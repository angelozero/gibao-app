package com.angelozero.gibao.app.config.exception;

import com.angelozero.gibao.app.config.error.Error;
import lombok.Getter;

@Getter
public class AppException extends RuntimeException {

    private final Error error;

    public AppException(Error error) {
        this.error = error;
    }
}

