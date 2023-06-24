package com.kanas.hbe.UnitTests;

import com.kanas.hbe.fixtures.UserFixtures;
import com.kanas.hbe.service.impl.EmailServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class EmailServiceUTests {

    @Mock
    private MailSender mailSender;

    @InjectMocks
    private EmailServiceImpl emailService;

    @Nested
    @DisplayName("Send Email UTests")
    class SendEmailTests {

        @Test
        void whenSendingRegistration_verifyMailSenderIsCalled() {

            // Given When
            emailService.sendRegistrationConfirmationEmail(UserFixtures.VALID_EMAIL, UUID.randomUUID().toString(),
                    true);

            // Then
            verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
        }

        @Test
        void whenSendingRegistrationIsNotFirstTry_verifyMailSenderIsCalled() {

            // Given When
            emailService.sendRegistrationConfirmationEmail(UserFixtures.VALID_EMAIL, UUID.randomUUID().toString(),
                    false);

            // Then
            verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
        }

        @Test
        void whenSending_verifyMailSenderIsCalled() {

            // Given When
            emailService.sendEmail(UserFixtures.VALID_EMAIL, UUID.randomUUID().toString(),
                    "text");

            // Then
            verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
        }

    }

}
