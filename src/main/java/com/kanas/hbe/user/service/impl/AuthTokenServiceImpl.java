package com.kanas.hbe.user.service.impl;

import com.kanas.hbe.user.domain.entity.AuthToken;
import com.kanas.hbe.user.repository.AuthTokenRepository;
import com.kanas.hbe.user.service.AuthTokenService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthTokenServiceImpl implements AuthTokenService {

    private final AuthTokenRepository authTokenRepository;

    public AuthTokenServiceImpl(AuthTokenRepository authTokenRepository) {
        this.authTokenRepository = authTokenRepository;
    }

    @Override
    public JpaRepository<AuthToken, Long> getJpaRepository() {
        return authTokenRepository;
    }

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public Class<AuthToken> getType() {
        return AuthToken.class;
    }
}
