package com.kanas.fixtbe.IntegrationTests;

import com.kanas.fixtbe.repository.RegionRepository;
import com.kanas.fixtbe.service.RegionService;
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
public class RegionServiceITest {

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private RegionService regionService;

    @Nested
    @DisplayName("Implemented Region ITests")
    class ImplementedRegionITests {

        @Test
        void getJpaRepository_noErrorsExpected() {

            // Given
            // When
            var regionRepo = regionService.getJpaRepository();

            // Then
            assertNotNull(regionRepo);
            assertEquals(regionRepository, regionRepo);

        }

        @Test
        void getLogger_noErrorExpected() {

            // Given
            // When
            var logger = regionService.getLogger();

            // Then
            assertNotNull(logger);
            assertEquals("com.kanas.fixtbe.service.impl.RegionServiceImpl", logger.getName());
        }

        @Test
        void getType_noErrorExpected() {

            // Given
            // When
            var regionType = regionService.getType();

            // Then
            assertNotNull(regionType);
            assertEquals("class com.kanas.fixtbe.domain.entity.Region", regionType.toString());
        }
    }
}
