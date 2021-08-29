package com.angelozero.gibao.app.config;

import com.angelozero.gibao.app.config.error.ApiError;
import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({AppException.class})
    public ResponseEntity<Object> errorServiceExceptionHandler(AppException ex) {
        ApiError apiError = getApiError(ex.getError());
        logErrorMessage(ex.getError().getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> unhandledException(Exception ex) {
        ApiError apiError = getApiError(Error.builder()
                .identifier(ex)
                .message("[Unhandled Error]: " + ex.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build());
        logErrorMessage(apiError.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    private ApiError getApiError(Error error) {
        return new ApiError(error.getMessage(), error.getStatus(), error.getIdentifier());
    }

    private void logErrorMessage(String message) {
        log.error(message);
    }
}
