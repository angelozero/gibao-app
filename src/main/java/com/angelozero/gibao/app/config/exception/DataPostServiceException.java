package com.angelozero.gibao.app.config.exception;

import com.angelozero.gibao.app.config.error.Error;
import lombok.Getter;

@Getter
public class DataPostServiceException extends RuntimeException {

    private final Error error;

    public DataPostServiceException(Error error, Exception e) {
        this.error = error;
    }

    public DataPostServiceException(Error error) {
        this.error = error;
    }
}

