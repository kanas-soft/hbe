package com.kanas.hbe.service.impl;

import com.kanas.hbe.domain.entity.User;
import com.kanas.hbe.domain.entity.VerificationToken;
import com.kanas.hbe.exception.InvalidVerificationTokenException;
import com.kanas.hbe.repository.VerificationTokenRepository;
import com.kanas.hbe.service.VerificationTokenService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private static Duration EXPIRY_DURATION = Duration.ofHours(1);

    private final VerificationTokenRepository verificationTokenRepository;

    public VerificationTokenServiceImpl(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Override
    public Optional<VerificationToken> getVerificationToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    @Override
    public void createVerificationToken(User user, String token) {
        // Set the expiry duration to one hour
        LocalDateTime expiryDate = LocalDateTime.now().plus(EXPIRY_DURATION);

        this.save(new VerificationToken(user, token, expiryDate));
    }

    @Override
    public VerificationToken regenerateVerificationToken(String existingVerificationToken) {
        var optionalVerificationToken = this.getVerificationToken(existingVerificationToken);
        if (optionalVerificationToken.isEmpty()) {
            throw new InvalidVerificationTokenException(existingVerificationToken);
        }

        var verificationToken = optionalVerificationToken.get();

        LocalDateTime expiryDate = LocalDateTime.now().plus(EXPIRY_DURATION);
        verificationToken.setExpiryDate(expiryDate);
        verificationToken.setToken(UUID.randomUUID().toString());

        return this.save(verificationToken);
    }

    @Override
    public JpaRepository<VerificationToken, Long> getJpaRepository() {
        return verificationTokenRepository;
    }

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public Class<VerificationToken> getType() {
        return VerificationToken.class;
    }
}
