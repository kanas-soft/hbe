package com.kanas.hbe.service.impl;

import com.kanas.hbe.domain.entity.ConfirmationToken;
import com.kanas.hbe.domain.entity.User;
import com.kanas.hbe.exception.InvalidConfirmationTokenException;
import com.kanas.hbe.repository.ConfirmationTokenRepository;
import com.kanas.hbe.service.ConfirmationTokenService;
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
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    private static final Duration EXPIRY_DURATION = Duration.ofHours(1);

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public ConfirmationTokenServiceImpl(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    @Override
    public Optional<ConfirmationToken> getConfirmationToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    @Override
    public void createConfirmationToken(User user, String token) {
        LocalDateTime expiryDate = LocalDateTime.now().plus(EXPIRY_DURATION);

        var confirmationToken = new ConfirmationToken(token, expiryDate, user);

        this.save(confirmationToken);
    }

    @Override
    public ConfirmationToken regenerateConfirmationToken(String existingConfirmationToken) {
        var optionalConfirmationToken = this.getConfirmationToken(existingConfirmationToken);

        if (optionalConfirmationToken.isEmpty()) {
            throw new InvalidConfirmationTokenException(existingConfirmationToken);
        }

        var confirmationToken = optionalConfirmationToken.get();

        LocalDateTime expiryDate = LocalDateTime.now().plus(EXPIRY_DURATION);
        confirmationToken.setExpiryDate(expiryDate);
        confirmationToken.setToken(UUID.randomUUID().toString());

        return this.save(confirmationToken);
    }

    @Override
    public JpaRepository<ConfirmationToken, UUID> getJpaRepository() {
        return confirmationTokenRepository;
    }

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public Class<ConfirmationToken> getType() {
        return ConfirmationToken.class;
    }
}
