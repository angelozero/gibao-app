package com.angelozero.gibao.app.config.exception;

import com.angelozero.gibao.app.config.error.Error;
import lombok.Getter;

@Getter
public class MapperException extends RuntimeException {

    private final Error error;

    public MapperException(Error error, Exception e) {
        this.error = error;
    }

    public MapperException(Error error) {
        this.error = error;
    }
}

