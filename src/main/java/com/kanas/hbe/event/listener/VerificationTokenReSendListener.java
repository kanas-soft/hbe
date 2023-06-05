package com.kanas.hbe.event.listener;

import com.kanas.hbe.domain.entity.User;
import com.kanas.hbe.event.OnResendTokenEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class VerificationTokenReSendListener implements ApplicationListener<OnResendTokenEvent> {

    private final MessageSource messageSource;
    private final JavaMailSender mailSender;

    public VerificationTokenReSendListener(MessageSource messageSource,
            JavaMailSender mailSender) {
        this.messageSource = messageSource;
        this.mailSender = mailSender;
    }

    @Override
    public void onApplicationEvent(OnResendTokenEvent event) {
        User user = event.getUser();
        String token = event.getToken();

        String recipientAddress = user.getEmail();
        String subject = "Resend Registration Confirmation";
        String confirmationUrl = event.getAppUrl() + "/confirmRegistration?token=" + token;
        String message = messageSource.getMessage("message.resendToken", null, event.getLocale());

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + "http://localhost:8080" + confirmationUrl);
        mailSender.send(email);
    }
}
