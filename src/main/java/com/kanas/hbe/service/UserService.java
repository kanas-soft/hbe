package com.kanas.hbe.service;

import com.kanas.hbe.domain.dto.RegistrationDto;
import com.kanas.hbe.domain.entity.User;
import com.kanas.hbe.exception.EmailAlreadyExistsException;
import com.kanas.hbe.exception.UsernameAlreadyExistsException;
import com.kanas.hbe.service.base.BaseService;

/**
 * Service interface, related to handle {@link User} operations for authorization and authentication.
 */
public interface UserService extends BaseService<User> {

    User registerNewUserAccount(RegistrationDto registrationDto) throws EmailAlreadyExistsException,
            UsernameAlreadyExistsException;
}
