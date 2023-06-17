package com.kanas.hbe.config;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ClockConfig {

    @Value("${time-travel.instant:null}")
    private String timeTravelInstant;

    @Value("${time-travel.zone:null}")
    private String timeTravelZone;

    @Bean
    @ConditionalOnProperty(value = "time-travel.enabled", havingValue = "false", matchIfMissing = true)
    public Clock defaultClock() {
        log.info("Using system default zone clock");
        return Clock.systemDefaultZone();
    }

    @Bean
    @ConditionalOnProperty(value = "time-travel.enabled", havingValue = "true")
    public Clock clock() {
        log.info("Using fixed clock {} {}", timeTravelInstant, timeTravelZone);
        return Clock.fixed(Instant.parse(timeTravelInstant), ZoneId.of(timeTravelZone));
    }
}