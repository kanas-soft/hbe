package com.kanas.hbe.service.impl;

import com.kanas.hbe.domain.dto.RegistrationDto;
import com.kanas.hbe.domain.entity.User;
import com.kanas.hbe.exception.EmailAlreadyExistsException;
import com.kanas.hbe.exception.UsernameAlreadyExistsException;
import com.kanas.hbe.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User registerNewUserAccount(RegistrationDto registrationDto) throws EmailAlreadyExistsException, UsernameAlreadyExistsException {
        return null;
    }

    @Override
    public JpaRepository<User, Long> getJpaRepository() {
        return null;
    }

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public Class<User> getType() {
        return User.class;
    }
}
