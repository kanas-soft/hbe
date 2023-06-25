package com.kanas.fixtbe.service.impl;

import com.kanas.fixtbe.domain.dto.RegistrationDto;
import com.kanas.fixtbe.domain.entity.Role;
import com.kanas.fixtbe.domain.entity.User;
import com.kanas.fixtbe.domain.enumeration.UserRole;
import com.kanas.fixtbe.exception.ConfirmationTokenExpiredException;
import com.kanas.fixtbe.exception.EmailAlreadyExistsException;
import com.kanas.fixtbe.exception.InvalidConfirmationTokenException;
import com.kanas.fixtbe.exception.UsernameAlreadyExistsException;
import com.kanas.fixtbe.mapper.UserMapper;
import com.kanas.fixtbe.repository.UserRepository;
import com.kanas.fixtbe.service.ConfirmationTokenService;
import com.kanas.fixtbe.service.RoleService;
import com.kanas.fixtbe.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final ConfirmationTokenService confirmationTokenService;
    private final Clock clock;

    public UserServiceImpl(UserRepository userRepository,
            RoleService roleService,
            PasswordEncoder passwordEncoder,
            ConfirmationTokenService confirmationTokenService,
            Clock clock) {
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.confirmationTokenService = confirmationTokenService;
        this.clock = clock;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " was not found!"));
    }

    @Override
    public User registerNewUserAccount(RegistrationDto registrationDto) throws EmailAlreadyExistsException,
            UsernameAlreadyExistsException {

        if (userRepository.existsByUsername(registrationDto.getUsername())) {
            throw new UsernameAlreadyExistsException("Username: " + registrationDto.getUsername() + " already exists!");
        }

        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            throw new EmailAlreadyExistsException("Email: " + registrationDto.getEmail() + " already exists!");
        }

        User user = UserMapper.toEntity(registrationDto);
        Role role = roleService.findByUserRole(UserRole.CLIENT);
        user.setRoles(new HashSet<>(Collections.singletonList(role)));
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

        return this.save(user);
    }

    @Override
    public void confirmRegistration(String token) {
        var optionalConfirmationToken = confirmationTokenService.getConfirmationToken(token);

        if (optionalConfirmationToken.isEmpty()) {
            throw new InvalidConfirmationTokenException(token);
        }

        var confirmationToken = optionalConfirmationToken.get();

        if (confirmationToken.getExpiryDate().isBefore(LocalDateTime.now(clock))) {
            throw new ConfirmationTokenExpiredException(token, confirmationToken.getExpiryDate());
        }

        User user = confirmationToken.getUser();
        user.setEnabled(true);

        this.save(user);
    }

    @Override
    public JpaRepository<User, UUID> getJpaRepository() {
        return userRepository;
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
