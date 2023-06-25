package com.kanas.fixtbe.util.constant;

public final class EmailConstants {

    public static final String REGISTRATION_CONFIRMATION_PATH = "/v1/users/confirm-registration?token=";
    public static final String REGISTRATION_CONFIRMATION_SUBJECT = "Registration Confirmation";
    public static final String REGISTRATION_CONFIRMATION_RESEND_SUBJECT = "Resend Registration Confirmation";

    private EmailConstants() {
    }
}
