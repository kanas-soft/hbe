package com.kanas.hbe.service.impl;

import com.kanas.hbe.domain.dto.RegistrationDto;
import com.kanas.hbe.domain.entity.User;
import com.kanas.hbe.exception.EmailAlreadyExistsException;
import com.kanas.hbe.exception.UsernameAlreadyExistsException;
import com.kanas.hbe.service.UserService;
import org.slf4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

//@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Override
    public void registerNewUserAccount(RegistrationDto registrationDto) throws EmailAlreadyExistsException,
            UsernameAlreadyExistsException {
    }

    @Override
    public JpaRepository<User, Long> getJpaRepository() {
        return null;
    }

    @Override
    public Logger getLogger() {
        return null;
    }

    @Override
    public Class<User> getType() {
        return User.class;
    }
}
