package com.angelozero.gibao.app.config.exception;

import com.angelozero.gibao.app.config.error.Error;
import lombok.Getter;

@Getter
public class MapperException extends AppException {

    public MapperException(Error error) {
        super(error);
    }
}

