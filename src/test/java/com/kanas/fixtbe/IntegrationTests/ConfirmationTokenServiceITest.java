package com.kanas.fixtbe.IntegrationTests;

import com.kanas.fixtbe.domain.entity.ConfirmationToken;
import com.kanas.fixtbe.domain.entity.User;
import com.kanas.fixtbe.exception.InvalidConfirmationTokenException;
import com.kanas.fixtbe.fixtures.UserFixtures;
import com.kanas.fixtbe.repository.ConfirmationTokenRepository;
import com.kanas.fixtbe.service.impl.ConfirmationTokenServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class ConfirmationTokenServiceITest {

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private ConfirmationTokenServiceImpl confirmationTokenService;

    @Autowired
    private Clock clock;

    @Nested
    @DisplayName("Get Confirmation Token ITests")
    class GetConfirmationToken {

        @Test
        void whenFetching_byToken_NoErrors() throws Exception {

            // Given When
            Optional<ConfirmationToken> optionalConfirmationToken = confirmationTokenService
                    .getConfirmationToken("f5fb51a1-b43d-46d7-9d91-9260d52d2b7a");

            // Then
            assertTrue(optionalConfirmationToken.isPresent());
        }

        @Test
        void whenFetching_byNonExistentToken_NoErrors() throws Exception {

            // Given When
            Optional<ConfirmationToken> optionalConfirmationToken = confirmationTokenService
                    .getConfirmationToken("30f268de-2822-46eb-9458-d25667797193");

            // Then
            assertFalse(optionalConfirmationToken.isPresent());
        }

    }

    @Nested
    @DisplayName("Save Confirmation Token ITests")
    class SaveConfirmationToken {

        @Test
        void whenCreating_Token_NoErrors() throws Exception {

            // Given
            User user = UserFixtures.createUser();

            // This is existing user in the h2 db.
            user.setId(UUID.fromString("de305d54-75b4-431b-adb2-eb6b9e546014"));

            // When
            confirmationTokenService.createConfirmationToken(user, "8ce266f3-18c7-4c61-892d-04f54a929c8f");

            // Then
            Optional<ConfirmationToken> optionalConfirmationToken = confirmationTokenService
                    .getConfirmationToken("8ce266f3-18c7-4c61-892d-04f54a929c8f");

            assertTrue(optionalConfirmationToken.isPresent());
        }

    }

    @Nested
    @DisplayName("Regenerate Confirmation Token ITests")
    class RegenerateConfirmationToken {

        @Test
        void whenRegenerating_Token_Error() throws Exception {

            // Given
            // Token thats not in the h2 db.
            String uuid = "8ce266ff-18c7-4c61-892d-04f54a929c8f";

            // When Then
            assertThrows(InvalidConfirmationTokenException.class,
                    () -> confirmationTokenService.regenerateConfirmationToken(uuid));
        }

        @Test
        void whenRegenerating_Token_NoError() throws Exception {

            // Given
            // Token that exists in the h2 db.
            String token = "e8a825a0-6d56-4ae6-bf28-5f8bfbfba6eb";
            LocalDateTime dateTime1 = LocalDateTime.of(2023, 6, 14, 14, 0, 0);

            // When
            ConfirmationToken confirmationToken = confirmationTokenService.regenerateConfirmationToken(token);

            // Then
            assertNotNull(confirmationToken);
            assertNotEquals(token, confirmationToken.getToken());
            assertTrue(dateTime1.isBefore(confirmationToken.getExpiryDate()));
        }

    }

    @Nested
    @DisplayName("Implemented Token ITests")
    class ImplementedTokenITests {

        @Test
        void getJpaRepository_noErrorsExpected() throws Exception {

            // Given
            // When
            var tokenRepo = confirmationTokenService.getJpaRepository();

            // Then
            assertNotNull(tokenRepo);
            assertEquals(confirmationTokenRepository, tokenRepo);

        }

        @Test
        void getLogger_noErrorExpected() throws Exception {

            // Given
            // When
            var logger = confirmationTokenService.getLogger();
            // Then
            assertNotNull(logger);
            assertEquals("com.kanas.fixtbe.service.impl.ConfirmationTokenServiceImpl", logger.getName());
        }

        @Test
        void getType_noErrorExpected() throws Exception {

            // Given
            // When
            var tokenType = confirmationTokenService.getType();
            // Then
            assertNotNull(tokenType);
            assertEquals("class com.kanas.fixtbe.domain.entity.ConfirmationToken", tokenType.toString());
        }
    }

}
