package com.angelozero.gibao.app.config.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Error {

    private static final long serialVersionUID = -4123458179859354491L;

    private String message;

    private HttpStatus status;

    private String field;

    private transient Object identifier;
}
