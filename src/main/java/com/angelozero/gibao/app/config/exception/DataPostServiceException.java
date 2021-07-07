package com.angelozero.gibao.app.config.exception;

import com.angelozero.gibao.app.config.error.Error;
import lombok.Getter;

@Getter
public class DataPostServiceException extends AppException {

    public DataPostServiceException(Error error) {
        super(error);
    }
}

