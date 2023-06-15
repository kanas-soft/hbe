package com.kanas.hbe.controller;

import com.kanas.hbe.domain.dto.RegistrationDto;
import com.kanas.hbe.domain.entity.ConfirmationToken;
import com.kanas.hbe.domain.entity.User;
import com.kanas.hbe.event.publisher.EventPublisher;
import com.kanas.hbe.exception.EmailAlreadyExistsException;
import com.kanas.hbe.exception.UsernameAlreadyExistsException;
import com.kanas.hbe.service.ConfirmationTokenService;
import com.kanas.hbe.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The UserController class handles HTTP requests related to user management.
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final EventPublisher eventPublisher;
    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;

    public UserController(EventPublisher eventPublisher,
            UserService userService,
            ConfirmationTokenService confirmationTokenService) {
        this.eventPublisher = eventPublisher;
        this.userService = userService;
        this.confirmationTokenService = confirmationTokenService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody RegistrationDto registrationDto) {
        log.debug("REST request to register a user!");

        try {
            User registeredUser = userService.registerNewUserAccount(registrationDto);

            eventPublisher.publishUserRegistrationEvent(registeredUser);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (EmailAlreadyExistsException | UsernameAlreadyExistsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/confirm-registration")
    public ResponseEntity<String> confirmRegistration(@RequestParam("token") String token) {
        log.debug("REST request to activate user with email confirmation!");

        userService.confirmRegistration(token);

        // Redirect to login page
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("https://www.google.com/"));
        return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);

    }

    @GetMapping("/resend-token")
    public ResponseEntity<String> resendConfirmationToken(@RequestParam("token") String token) {
        log.debug("REST request to resend confirmation token for user!");

        ConfirmationToken confirmationToken = confirmationTokenService.regenerateConfirmationToken(token);

        eventPublisher.publishResendTokenEvent(confirmationToken);

        // Redirect to login page
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("https://www.google.com/"));
        return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
    }
}