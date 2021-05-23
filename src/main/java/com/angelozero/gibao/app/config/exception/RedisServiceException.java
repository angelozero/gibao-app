package com.angelozero.gibao.app.config.exception;

import com.angelozero.gibao.app.config.error.Error;
import lombok.Getter;

@Getter
public class RedisServiceException extends RuntimeException {

    private final Error error;

    public RedisServiceException(Error error, Exception e) {
        this.error = error;
    }

    public RedisServiceException(Error error) {
        this.error = error;
    }
}

