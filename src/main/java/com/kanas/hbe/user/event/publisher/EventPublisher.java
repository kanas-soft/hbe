package com.kanas.hbe.user.event.publisher;

import com.kanas.hbe.user.domain.entity.User;
import com.kanas.hbe.user.domain.entity.VerificationToken;
import com.kanas.hbe.user.event.OnRegistrationCompleteEvent;
import com.kanas.hbe.user.event.OnResendTokenEvent;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {

    private static final String APP_URL = "http://%s:%s%s";

    private final ApplicationEventPublisher eventPublisher;

    public EventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishUserRegistrationEvent(User registeredUser, HttpServletRequest request) {
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registeredUser, request.getLocale(),
                String.format(APP_URL, request.getServerName(), request.getServerPort(), request.getContextPath())));
    }

    public void publishResendTokenEvent(VerificationToken token, HttpServletRequest request) {
        eventPublisher.publishEvent(new OnResendTokenEvent(token.getUser(), token.getToken(), request.getLocale(),
                String.format(APP_URL, request.getServerName(), request.getServerPort(), request.getContextPath())));
    }
}
