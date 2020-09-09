package com.atanasradkov.primes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotSupportedInputException extends RuntimeException{
    public NotSupportedInputException(String message) {
        super(message);
    }
}
