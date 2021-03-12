package com.angelozero.gibao.app.config.exception;

import com.angelozero.gibao.app.config.error.Error;
import lombok.Getter;

@Getter
public class PostDataServiceException extends RuntimeException {

    private final Error error;

    public PostDataServiceException(Error error, Exception e) {
        this.error = error;
    }

    public PostDataServiceException(Error error) {
        this.error = error;
    }
}

