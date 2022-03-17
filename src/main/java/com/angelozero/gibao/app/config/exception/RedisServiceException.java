package com.angelozero.gibao.app.config.exception;

import com.angelozero.gibao.app.config.error.Error;
import lombok.Getter;

@Getter
public class RedisServiceException extends AppException {

    public RedisServiceException(Error error) {
        super(error);
    }
}

