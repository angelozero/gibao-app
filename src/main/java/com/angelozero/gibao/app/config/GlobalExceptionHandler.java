package com.angelozero.gibao.app.config;

import com.angelozero.gibao.app.config.error.ApiError;
import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.DataPostServiceException;
import com.angelozero.gibao.app.config.exception.MapperException;
import com.angelozero.gibao.app.config.exception.PokemonApiException;
import com.angelozero.gibao.app.config.exception.RedisServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({DataPostServiceException.class})
    public ResponseEntity<Object> postDataServiceExceptionHandler(DataPostServiceException ex) {
        ApiError apiError = getApiError(ex.getError());
        logErrorMessage(ex.getError().getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({MapperException.class})
    public ResponseEntity<Object> mapperExceptionHandler(MapperException ex) {
        ApiError apiError = getApiError(ex.getError());
        logErrorMessage(ex.getError().getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({PokemonApiException.class})
    public ResponseEntity<Object> pokemonApiExceptionHandler(PokemonApiException ex) {
        ApiError apiError = getApiError(ex.getError());
        logErrorMessage(ex.getError().getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({RedisServiceException.class})
    public ResponseEntity<Object> redisServiceExceptionHandler(RedisServiceException ex) {
        ApiError apiError = getApiError(ex.getError());
        logErrorMessage(ex.getError().getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    private ApiError getApiError(Error error) {
        return new ApiError(error.getMessage(), error.getStatus(), error.getIdentifier());
    }

    private void logErrorMessage(String message) {
        log.error(message);
    }
}
