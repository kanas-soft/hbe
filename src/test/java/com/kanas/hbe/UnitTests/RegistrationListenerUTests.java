package com.kanas.hbe.UnitTests;

import com.kanas.hbe.domain.entity.User;
import com.kanas.hbe.event.OnRegistrationCompleteEvent;
import com.kanas.hbe.event.listener.RegistrationListener;
import com.kanas.hbe.fixtures.UserFixtures;
import com.kanas.hbe.service.ConfirmationTokenService;
import com.kanas.hbe.service.EmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class RegistrationListenerUTests {

    @Mock
    private ConfirmationTokenService confirmationTokenService;

    @Mock
    private EmailService emailService;

    @Captor
    private ArgumentCaptor<String> emailCaptor;

    @InjectMocks
    private RegistrationListener registrationListener;

    @Test
    void onApplicationEvent_ValidEvent_Success() {
        // Initialize mocks and inject dependencies
        // MockitoAnnotations.openMocks(this);

        // Create a test user
        User user = UserFixtures.createUser();

        // Create an event with the test user
        OnRegistrationCompleteEvent event = new OnRegistrationCompleteEvent(user, "http://localhost:8080");

        // Call the onApplicationEvent method
        registrationListener.onApplicationEvent(event);

        // Verify that createConfirmationToken and sendRegistrationConfirmationEmail
        // methods were called
        verify(confirmationTokenService, times(1)).createConfirmationToken(any(User.class), any(String.class));
        verify(emailService, times(1)).sendRegistrationConfirmationEmail(eq(user.getEmail()), emailCaptor.capture(),
                eq(true));

        // Get the captured email token argument
        String emailToken = emailCaptor.getValue();
        assertNotNull(emailToken);
    }
}