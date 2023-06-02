package com.kanas.hbe.user.service;

import com.kanas.hbe.user.domain.entity.User;
import com.kanas.hbe.user.domain.entity.VerificationToken;
import com.kanas.hbe.user.service.base.BaseService;

import java.util.Optional;

public interface VerificationTokenService extends BaseService<VerificationToken> {
    void createVerificationToken(User user, String token);

    VerificationToken regenerateVerificationToken(String token);

    Optional<VerificationToken> getVerificationToken(String token);
}
