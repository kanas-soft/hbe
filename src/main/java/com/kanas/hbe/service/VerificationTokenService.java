package com.kanas.hbe.service;

import com.kanas.hbe.domain.entity.User;
import com.kanas.hbe.domain.entity.VerificationToken;
import com.kanas.hbe.service.base.BaseService;

import java.util.Optional;

public interface VerificationTokenService extends BaseService<VerificationToken> {
    void createVerificationToken(User user, String token);

    VerificationToken regenerateVerificationToken(String token);

    Optional<VerificationToken> getVerificationToken(String token);
}
