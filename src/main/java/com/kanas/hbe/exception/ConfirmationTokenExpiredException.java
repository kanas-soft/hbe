package com.kanas.hbe.exception;

import java.time.LocalDateTime;

public class ConfirmationTokenExpiredException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Confirmation token: %s with expiry date: %s has expired!";

    public ConfirmationTokenExpiredException(String token, LocalDateTime expiryDate) {
        super(String.format(ERROR_MESSAGE, token, expiryDate.toString()));
    }
}