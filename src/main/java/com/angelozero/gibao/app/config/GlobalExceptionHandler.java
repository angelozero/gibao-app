package com.angelozero.gibao.app.config;

import com.angelozero.gibao.app.config.exception.MapperException;
import com.angelozero.gibao.app.config.exception.PokemonApiException;
import com.angelozero.gibao.app.config.exception.DataPostServiceException;
import com.angelozero.gibao.app.config.error.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({DataPostServiceException.class})
    public ResponseEntity<Object> postDataServiceExceptionHandler(DataPostServiceException ex) {

        ApiError apiError = new ApiError(ex.getError().getMessage(), ex.getError().getStatus(), ex.getError().getIdentifier());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({MapperException.class})
    public ResponseEntity<Object> mapperExceptionHandler(MapperException ex) {
        ApiError apiError = new ApiError(ex.getError().getMessage(), ex.getError().getStatus(), ex.getError().getIdentifier());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({PokemonApiException.class})
    public ResponseEntity<Object> pokemonApiExceptionHandler(PokemonApiException ex) {
        ApiError apiError = new ApiError(ex.getError().getMessage(), ex.getError().getStatus(), ex.getError().getIdentifier());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
