package com.angelozero.gibao.app.config.exception;

import com.angelozero.gibao.app.config.error.Error;
import lombok.Getter;

@Getter
public class PokemonApiException extends AppException {

    public PokemonApiException(Error error) {
        super(error);
    }
}

