package com.kanas.fixtbe.event.publisher;

import com.kanas.fixtbe.domain.entity.ConfirmationToken;
import com.kanas.fixtbe.domain.entity.User;
import com.kanas.fixtbe.event.OnRegistrationCompleteEvent;
import com.kanas.fixtbe.event.OnResendTokenEvent;
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