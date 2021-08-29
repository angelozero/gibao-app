package com.angelozero.gibao.app.config.exception;

import com.angelozero.gibao.app.config.error.Error;
import lombok.Getter;

@Getter
public class JsonReaderIntegrationTestException extends AppException {

    public JsonReaderIntegrationTestException(Error error) {
        super(error);
    }
}

