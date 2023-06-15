package com.kanas.hbe.IntegrationTests;

import com.kanas.hbe.domain.entity.ConfirmationToken;
import com.kanas.hbe.repository.ConfirmationTokenRepository;
import com.kanas.hbe.service.impl.ConfirmationTokenServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class ConfirmationTokenServiceITest {

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private ConfirmationTokenServiceImpl confirmationTokenService;

    @Nested
    @DisplayName("Get Confirmation Token ITests")
    class getConfirmationToken {

        @Test
        void whenFetching_byToken_NoErrors() throws Exception {

            // Given When
            Optional<ConfirmationToken> optionalConfirmationToken = confirmationTokenService
                    .getConfirmationToken("8ce266f2-18c7-4c61-892d-04f54a929c8f");

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
            assertEquals("com.kanas.hbe.service.impl.ConfirmationTokenServiceImpl", logger.getName());
        }

        @Test
        void getType_noErrorExpected() throws Exception {

            // Given
            // When
            var tokenType = confirmationTokenService.getType();
            // Then
            assertNotNull(tokenType);
            assertEquals("class com.kanas.hbe.domain.entity.ConfirmationToken", tokenType.toString());
        }
    }

}
