package com.kanas.hbe.IntegrationTests;

import com.kanas.hbe.repository.ReviewRepository;
import com.kanas.hbe.service.ReviewService;
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
public class ReviewServiceITest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewService reviewService;

    @Nested
    @DisplayName("Implemented Review ITests")
    class ImplementedReviewITests {

        @Test
        void getJpaRepository_noErrorsExpected() {

            // Given
            // When
            var reviewRepo = reviewService.getJpaRepository();

            // Then
            assertNotNull(reviewRepo);
            assertEquals(reviewRepository, reviewRepo);

        }

        @Test
        void getLogger_noErrorExpected() {

            // Given
            // When
            var logger = reviewService.getLogger();

            // Then
            assertNotNull(logger);
            assertEquals("com.kanas.hbe.service.impl.ReviewServiceImpl", logger.getName());
        }

        @Test
        void getType_noErrorExpected() {

            // Given
            // When
            var reviewType = reviewService.getType();

            // Then
            assertNotNull(reviewType);
            assertEquals("class com.kanas.hbe.domain.entity.Review", reviewType.toString());
        }
    }
}
