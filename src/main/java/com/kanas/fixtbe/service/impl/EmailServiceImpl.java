package com.kanas.fixtbe.service.impl;

import com.kanas.fixtbe.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import static com.kanas.fixtbe.util.constant.EmailConstants.*;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${fixt.base-url}")
    private String baseUrl;

    private final MailSender mailSender;

    public EmailServiceImpl(MailSender javaMailSender) {
        this.mailSender = javaMailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage email = new SimpleMailMessage();

        // We should use a dedicated email address for this
        email.setFrom("todorv1@gmail.com");
        email.setTo(to);
        email.setSubject(subject);
        email.setText(text);

        mailSender.send(email);
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
