package com.kanas.hbe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class InvalidVerificationTokenException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Verification token: %s is not valid!";

    public InvalidVerificationTokenException(String token) {
        super(String.format(ERROR_MESSAGE, token));
    }
}
