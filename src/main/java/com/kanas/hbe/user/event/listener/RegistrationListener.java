package com.kanas.hbe.user.event.listener;

import com.kanas.hbe.user.domain.entity.User;
import com.kanas.hbe.user.event.OnRegistrationCompleteEvent;
import com.kanas.hbe.user.service.VerificationTokenService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final VerificationTokenService tokenService;
    private final MessageSource messageSource;
    private final JavaMailSender mailSender;

    public RegistrationListener(VerificationTokenService tokenService,
            MessageSource messageSource,
            JavaMailSender mailSender) {
        this.tokenService = tokenService;
        this.messageSource = messageSource;
        this.mailSender = mailSender;
    }

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        tokenService.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = event.getAppUrl() + "/confirmRegistration?token=" + token;
        String message = messageSource.getMessage("message.regSuccessful", null, event.getLocale());

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + "http://localhost:8080" + confirmationUrl);
        mailSender.send(email);
    }
}
