package com.kanas.hbe.user.service;

import com.kanas.hbe.user.domain.dto.RegistrationDto;
import com.kanas.hbe.user.domain.entity.User;
import com.kanas.hbe.user.exception.EmailAlreadyExistsException;
import com.kanas.hbe.user.exception.UsernameAlreadyExistsException;
import com.kanas.hbe.user.service.base.BaseService;

/**
 * Service interface, related to handle {@link User} operations for
 * authorization and authentication.
 */
public interface UserService extends BaseService<User> {

    User registerNewUserAccount(RegistrationDto registrationDto) throws EmailAlreadyExistsException,
            UsernameAlreadyExistsException;;

    void confirmRegistration(String token);
}
