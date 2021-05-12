package com.angelozero.gibao.app.config.exception;

import com.angelozero.gibao.app.config.error.Error;
import lombok.Getter;

@Getter
public class PokemonApiException extends RuntimeException {

    private final Error error;

    public PokemonApiException(Error error, Exception e) {
        this.error = error;
    }

    public PokemonApiException(Error error) {
        this.error = error;
    }
}

