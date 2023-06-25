package com.kanas.fixtbe.event.listener;

import com.kanas.fixtbe.domain.entity.User;
import com.kanas.fixtbe.event.OnRegistrationCompleteEvent;
import com.kanas.fixtbe.service.ConfirmationTokenService;
import com.kanas.fixtbe.service.EmailService;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final ConfirmationTokenService confirmationTokenService;
    private final EmailService emailService;

    public RegistrationListener(ConfirmationTokenService confirmationTokenService,
                                EmailService emailService) {
        this.confirmationTokenService = confirmationTokenService;
        this.emailService = emailService;
    }

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();

        confirmationTokenService.createConfirmationToken(user, token);

        emailService.sendRegistrationConfirmationEmail(user.getEmail(), token, true);
    }
}
