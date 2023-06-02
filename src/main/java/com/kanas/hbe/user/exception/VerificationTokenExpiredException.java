package com.kanas.hbe.user.exception;

import java.time.LocalDateTime;

public class VerificationTokenExpiredException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Verification token: %s | expiry date: %s has expired!";

    public VerificationTokenExpiredException(String token, LocalDateTime expiryDate) {
        super(String.format(ERROR_MESSAGE, token, expiryDate.toString()));
    }
}
