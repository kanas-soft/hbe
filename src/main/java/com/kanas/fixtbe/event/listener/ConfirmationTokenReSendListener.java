package com.kanas.fixtbe.event.listener;

import com.kanas.fixtbe.domain.entity.User;
import com.kanas.fixtbe.event.OnResendTokenEvent;
import com.kanas.fixtbe.service.EmailService;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ConfirmationTokenReSendListener implements ApplicationListener<OnResendTokenEvent> {

    private final EmailService emailService;

    public ConfirmationTokenReSendListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void onApplicationEvent(OnResendTokenEvent event) {
        User user = event.getUser();
        String token = event.getToken();

        emailService.sendRegistrationConfirmationEmail(user.getEmail(), token, false);
    }
}