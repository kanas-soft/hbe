package com.kanas.fixtbe.UnitTests;

import com.kanas.fixtbe.domain.entity.User;
import com.kanas.fixtbe.event.OnResendTokenEvent;
import com.kanas.fixtbe.event.listener.ConfirmationTokenReSendListener;
import com.kanas.fixtbe.fixtures.UserFixtures;
import com.kanas.fixtbe.service.EmailService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class ConfirmationTokenReSendListenerUTests {

    @Mock
    private EmailService emailService;

    @InjectMocks
    private ConfirmationTokenReSendListener listener;

    @Nested
    @DisplayName("Send Email UTests")
    class OnApplicationEventTests {

        @Test
        void testOnApplicationEvent() {
            // Given
            User user = UserFixtures.createUser();
            String token = UUID.randomUUID().toString();

            // WHEN
            OnResendTokenEvent event = new OnResendTokenEvent(user, token, "http://localhost:8080");

            listener.onApplicationEvent(event);

            // THEN
            Mockito.verify(emailService, Mockito.times(1))
                    .sendRegistrationConfirmationEmail(user.getEmail(), token, false);
        }
    }

}