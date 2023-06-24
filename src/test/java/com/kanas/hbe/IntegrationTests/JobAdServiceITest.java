package com.kanas.hbe.IntegrationTests;

import com.kanas.hbe.repository.JobAdRepository;
import com.kanas.hbe.service.JobAdService;
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
public class JobAdServiceITest {

    @Autowired
    private JobAdRepository jobAdRepository;

    @Autowired
    private JobAdService jobAdService;

    @Nested
    @DisplayName("Implemented JobAd ITests")
    class ImplementedJobAdITests {

        @Test
        void getJpaRepository_noErrorsExpected() {

            // Given
            // When
            var jobAdRepo = jobAdService.getJpaRepository();

            // Then
            assertNotNull(jobAdRepo);
            assertEquals(jobAdRepository, jobAdRepo);

        }

        @Test
        void getLogger_noErrorExpected() {

            // Given
            // When
            var logger = jobAdService.getLogger();

            // Then
            assertNotNull(logger);
            assertEquals("com.kanas.hbe.service.impl.JobAdServiceImpl", logger.getName());
        }

        @Test
        void getType_noErrorExpected() {

            // Given
            // When
            var expertiseType = jobAdService.getType();

            // Then
            assertNotNull(expertiseType);
            assertEquals("class com.kanas.hbe.domain.entity.JobAd", expertiseType.toString());
        }
    }
}
