package com.kanas.hbe.service.impl;

import com.kanas.hbe.domain.dto.RegistrationDto;
import com.kanas.hbe.domain.entity.Role;
import com.kanas.hbe.domain.entity.User;
import com.kanas.hbe.domain.enumeration.UserRole;
import com.kanas.hbe.exception.EmailAlreadyExistsException;
import com.kanas.hbe.exception.UsernameAlreadyExistsException;
import com.kanas.hbe.mapper.UserMapper;
import com.kanas.hbe.repo.UserRepository;
import com.kanas.hbe.service.UserService;

import lombok.extern.slf4j.Slf4j;

import com.kanas.hbe.service.RoleService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final RoleService roleService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService,
            PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " was not found!"));
    }

    @Override
    public void registerNewUserAccount(RegistrationDto registrationDto) throws EmailAlreadyExistsException,
            UsernameAlreadyExistsException {

        if (userRepository.existsByUsername(registrationDto.getUsername())) {
            throw new UsernameAlreadyExistsException("Username: " + registrationDto.getUsername() + " already exists!");
        }

        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            throw new EmailAlreadyExistsException("Email: " + registrationDto.getEmail() + " already exists!");
        }

        User user = UserMapper.toEntity(registrationDto);
        Role role = roleService.findByRole(UserRole.CLIENT);
        user.setRoles(new HashSet<>(Arrays.asList(role)));
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

        userRepository.save(user);
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
