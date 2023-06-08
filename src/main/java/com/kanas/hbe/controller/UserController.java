package com.kanas.hbe.controller;

import com.kanas.hbe.domain.dto.RegistrationDto;
import com.kanas.hbe.exception.EmailAlreadyExistsException;
import com.kanas.hbe.exception.UsernameAlreadyExistsException;
import com.kanas.hbe.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody RegistrationDto registrationDto) {
        log.debug("REST request to register a user!");

        try {
            userService.registerNewUserAccount(registrationDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (EmailAlreadyExistsException | UsernameAlreadyExistsException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}