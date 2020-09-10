package com.atanasradkov.primes.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Exception handler for custom messages implementation.
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotSupportedInputException.class)
    protected ResponseEntity<Object> handleInvalidRequestError(NotSupportedInputException ex,
                                                               WebRequest request) {
        return buildResponse(ex, HttpStatus.BAD_REQUEST, request);
    }

    private ResponseEntity<Object> buildResponse(Exception error, HttpStatus status, WebRequest request) {
        ApiError apiErr = ApiError.builder().status(status.value()).reason(status.getReasonPhrase()).message(error.getMessage()).build();
        return handleExceptionInternal(error, apiErr, new HttpHeaders(), status, request);
    }
}