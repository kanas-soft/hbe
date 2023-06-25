package com.kanas.fixtbe.service;

import com.kanas.fixtbe.domain.dto.RegistrationDto;
import com.kanas.fixtbe.domain.entity.User;
import com.kanas.fixtbe.exception.EmailAlreadyExistsException;
import com.kanas.fixtbe.exception.UsernameAlreadyExistsException;
import com.kanas.fixtbe.service.base.BaseService;

/**
 * Service interface, related to handle {@link User} operations for authorization and authentication.
 */
public interface UserService extends BaseService<User> {

    User registerNewUserAccount(RegistrationDto registrationDto) throws EmailAlreadyExistsException,
            UsernameAlreadyExistsException;

    void confirmRegistration(String token);
}
