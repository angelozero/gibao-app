package com.angelozero.gibao.front.configuration.exception.handler;

import com.angelozero.gibao.app.config.exception.MapperException;
import com.angelozero.gibao.app.config.exception.PostDataServiceException;
import com.angelozero.gibao.front.configuration.exception.error.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({PostDataServiceException.class})
    public ResponseEntity<Object> postDataServiceExceptionHandler(PostDataServiceException ex) {
        ApiError apiError = new ApiError(ex.getError().getMessage(), ex.getError().getStatus(), ex.getError().getIdentifier());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({MapperException.class})
    public ResponseEntity<Object> mapperExceptionHandler(MapperException ex) {
        ApiError apiError = new ApiError(ex.getError().getMessage(), ex.getError().getStatus(), ex.getError().getIdentifier());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
