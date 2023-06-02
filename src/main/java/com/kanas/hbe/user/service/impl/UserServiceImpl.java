package com.kanas.hbe.user.service.impl;

import com.kanas.hbe.user.domain.dto.RegistrationDto;
import com.kanas.hbe.user.domain.entity.Contact;
import com.kanas.hbe.user.domain.entity.User;
import com.kanas.hbe.user.exception.EmailAlreadyExistsException;
import com.kanas.hbe.user.exception.InvalidVerificationTokenException;
import com.kanas.hbe.user.exception.UsernameAlreadyExistsException;
import com.kanas.hbe.user.exception.VerificationTokenExpiredException;
import com.kanas.hbe.user.repository.UserRepository;
import com.kanas.hbe.user.service.ContactService;
import com.kanas.hbe.user.service.UserService;
import com.kanas.hbe.user.service.VerificationTokenService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ContactService contactService;
    private final VerificationTokenService verificationTokenService;

    public UserServiceImpl(UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            ContactService contactService,
            VerificationTokenService verificationTokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.contactService = contactService;
        this.verificationTokenService = verificationTokenService;
    }

    @Override
    public User registerNewUserAccount(RegistrationDto registrationDto) throws EmailAlreadyExistsException,
            UsernameAlreadyExistsException {
        if (emailExists(registrationDto.getEmail())) {
            log.debug("User with email: {} already exists!", registrationDto.getEmail());
            throw new EmailAlreadyExistsException(registrationDto.getEmail());
        }

        if (usernameExists(registrationDto.getUsername())) {
            log.debug("Username: {} already exists!", registrationDto.getUsername());
            throw new UsernameAlreadyExistsException(registrationDto.getUsername());
        }

        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

        Contact contact = new Contact();
        contact.setFirstName(registrationDto.getFirstName());
        contact.setLastName(registrationDto.getLastName());
        contact.setAddress(registrationDto.getAddress());
        contact.setPhoneNumber(registrationDto.getPhoneNumber());

        user.setContact(contact);
        contact.setUser(user);

        user = this.save(user);
        contactService.save(contact);

        return user;
    }

    @Override
    public void confirmRegistration(String token) {
        var optionalVerificationToken = verificationTokenService.getVerificationToken(token);
        if (optionalVerificationToken.isEmpty()) {
            throw new InvalidVerificationTokenException(token);
        }

        var verificationToken = optionalVerificationToken.get();

        if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new VerificationTokenExpiredException(token, verificationToken.getExpiryDate());
        }

        User user = verificationToken.getUser();
        user.setEnabled(true);
        this.save(user);
    }

    @Override
    public JpaRepository<User, Long> getJpaRepository() {
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

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
