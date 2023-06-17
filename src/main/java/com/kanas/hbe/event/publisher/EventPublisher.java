package com.kanas.hbe.event.publisher;

import com.kanas.hbe.domain.entity.ConfirmationToken;
import com.kanas.hbe.domain.entity.User;
import com.kanas.hbe.event.OnRegistrationCompleteEvent;
import com.kanas.hbe.event.OnResendTokenEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {

    @Value("${fixt.base-url}")
    private String baseUrl;

    private final ApplicationEventPublisher appEventPublisher;

    public EventPublisher(ApplicationEventPublisher eventPublisher) {
        this.appEventPublisher = eventPublisher;
    }

    public void publishUserRegistrationEvent(User registeredUser) {
        appEventPublisher.publishEvent(new OnRegistrationCompleteEvent(registeredUser, baseUrl));
    }

    public void publishResendTokenEvent(ConfirmationToken token) {
        appEventPublisher.publishEvent(new OnResendTokenEvent(token.getUser(), token.getToken(), baseUrl));
    }
}