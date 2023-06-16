package com.kanas.hbe.UnitTests;

import com.kanas.hbe.domain.entity.ConfirmationToken;
import com.kanas.hbe.domain.entity.User;
import com.kanas.hbe.event.OnRegistrationCompleteEvent;
import com.kanas.hbe.event.OnResendTokenEvent;
import com.kanas.hbe.event.publisher.EventPublisher;
import com.kanas.hbe.fixtures.UserFixtures;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class EventPublisherUTests {

    @Mock
    private ApplicationEventPublisher appEventPublisher;

    @InjectMocks
    private EventPublisher eventPublisher;

    @Test
    void testPublishUserRegistrationEvent() {
        // GIVEN
        User user = UserFixtures.createUser();

        // WHEN
        eventPublisher.publishUserRegistrationEvent(user);

        // THEN
        ArgumentCaptor<OnRegistrationCompleteEvent> captor = ArgumentCaptor.forClass(OnRegistrationCompleteEvent.class);
        verify(this.appEventPublisher).publishEvent(captor.capture());
        OnRegistrationCompleteEvent event = captor.getValue();

        assertEquals(user, event.getUser());
    }

    @Test
    void testPublishResendTokenEvent() {
        // GIVEN
        ConfirmationToken token = new ConfirmationToken();
        token.setUser(UserFixtures.createUser());
        token.setToken(UUID.randomUUID().toString());

        // WHEN
        eventPublisher.publishResendTokenEvent(token);

        // THEN
        ArgumentCaptor<OnResendTokenEvent> captor = ArgumentCaptor.forClass(OnResendTokenEvent.class);
        verify(this.appEventPublisher).publishEvent(captor.capture());
        OnResendTokenEvent event = captor.getValue();

        assertEquals(token.getUser(), event.getUser());
        assertEquals(token.getToken(), event.getToken());
    }
}
