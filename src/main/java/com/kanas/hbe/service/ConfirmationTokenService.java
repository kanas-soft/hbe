package com.kanas.hbe.service;

import com.kanas.hbe.domain.entity.ConfirmationToken;
import com.kanas.hbe.domain.entity.User;
import com.kanas.hbe.service.base.BaseService;

import java.util.Optional;

/**
 * The ConfirmationTokenService interface provides methods for managing confirmation tokens.
 * It extends the BaseService interface with the type parameter set to ConfirmationToken.
 */
public interface ConfirmationTokenService extends BaseService<ConfirmationToken> {

    /**
     * Creates a confirmation token for the specified user with the given token string identifier,
     * with a default expiry time of one hour.
     *
     * @param user  the user for whom the confirmation token is created
     * @param token the confirmation token to be created by uuid generation
     */
    void createConfirmationToken(User user, String token);

    /**
     * Regenerates a confirmation token, since the old one has reached the expiry date.
     *
     * @param token  to be regenerated
     * @return the regenerated confirmation token
     */
    ConfirmationToken regenerateConfirmationToken(String token);

    /**
     * Retrieves the confirmation token associated with the specified token uuid key.
     *
     * @param token uuid key to retrieve by
     * @return an optional containing the retrieved confirmation token, or an empty optional if not found
     */
    Optional<ConfirmationToken> getConfirmationToken(String token);
}
