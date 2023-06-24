package com.kanas.hbe.IntegrationTests;

import com.kanas.hbe.repository.ExpertiseRepository;
import com.kanas.hbe.service.ExpertiseService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class ExpertiseServiceITest {

    @Autowired
    private ExpertiseRepository expertiseRepository;

    @Autowired
    private ExpertiseService expertiseService;

    @Nested
    @DisplayName("Implemented Expertise ITests")
    class ImplementedExpertiseITests {

        @Test
        void getJpaRepository_noErrorsExpected() {

            // Given
            // When
            var expertiseRepo = expertiseService.getJpaRepository();

            // Then
            assertNotNull(expertiseRepo);
            assertEquals(expertiseRepository, expertiseRepo);

        }

        @Test
        void getLogger_noErrorExpected() {

            // Given
            // When
            var logger = expertiseService.getLogger();

            // Then
            assertNotNull(logger);
            assertEquals("com.kanas.hbe.service.impl.ExpertiseServiceImpl", logger.getName());
        }

        @Test
        void getType_noErrorExpected() {

            // Given
            // When
            var expertiseType = expertiseService.getType();

            // Then
            assertNotNull(expertiseType);
            assertEquals("class com.kanas.hbe.domain.entity.Expertise", expertiseType.toString());
        }
    }
}
