package com.kanas.hbe.service.impl;

import com.kanas.hbe.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static com.kanas.hbe.util.constant.EmailConstants.*;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${fixt.base-url}")
    private String baseUrl;

    private final JavaMailSender javaMailSender;

    // SMTP server settings needed in order to have JavaMailSender bean work.
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage email = new SimpleMailMessage();

        email.setTo(to);
        email.setSubject(subject);
        email.setText(text);

        javaMailSender.send(email);
    }

    @Override
    public void sendRegistrationConfirmationEmail(String recipientEmail, String confirmationToken, boolean isFirstTry) {
        String confirmationUrl = REGISTRATION_CONFIRMATION_PATH + confirmationToken;
        String text = "\r\n" + baseUrl + confirmationUrl;

        if (isFirstTry) {
            this.sendEmail(recipientEmail, REGISTRATION_CONFIRMATION_SUBJECT, text);
        } else {
            this.sendEmail(recipientEmail, REGISTRATION_CONFIRMATION_RESEND_SUBJECT, text);
        }
    }

}
