package com.kanas.hbe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class InvalidConfirmationTokenException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Confirmation token: %s is not valid!";

    public InvalidConfirmationTokenException(String token) {
        super(String.format(ERROR_MESSAGE, token));
    }
}
