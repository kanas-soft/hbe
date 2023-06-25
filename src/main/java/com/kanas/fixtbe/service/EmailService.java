package com.kanas.fixtbe.service;

public interface EmailService {

    void sendEmail(String to, String subject, String text);

    void sendRegistrationConfirmationEmail(String recipientEmail, String confirmationToken, boolean isFirstTry);

}
