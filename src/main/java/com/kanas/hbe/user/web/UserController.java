package com.kanas.hbe.user.web;

import com.kanas.hbe.user.domain.dto.RegistrationDto;
import com.kanas.hbe.user.domain.entity.User;
import com.kanas.hbe.user.domain.entity.VerificationToken;
import com.kanas.hbe.user.event.publisher.EventPublisher;
import com.kanas.hbe.user.exception.EmailAlreadyExistsException;
import com.kanas.hbe.user.exception.UsernameAlreadyExistsException;
import com.kanas.hbe.user.service.UserService;
import com.kanas.hbe.user.service.VerificationTokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller to manage {@link User} relates APIs.
 */
@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final VerificationTokenService verificationTokenService;
    private final EventPublisher eventPublisher;

    public UserController(UserService userService,
            VerificationTokenService verificationTokenService,
            EventPublisher eventPublisher) {
        this.userService = userService;
        this.verificationTokenService = verificationTokenService;
        this.eventPublisher = eventPublisher;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody RegistrationDto registrationDto,
            HttpServletRequest request) {
        log.debug("REST request to register a user!");

        try {
            User registeredUser = userService.registerNewUserAccount(registrationDto);

            eventPublisher.publishUserRegistrationEvent(registeredUser, request);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (EmailAlreadyExistsException | UsernameAlreadyExistsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/confirm-registration")
    public ResponseEntity<String> confirmRegistration(@RequestParam("token") String token,
            HttpServletRequest request) {
        log.debug("REST request to activate user with email confirmation!");

        userService.confirmRegistration(token);

        return ResponseEntity.ok("redirect:/login?lang=" + request.getLocale().getLanguage());
    }

    @GetMapping("/resend-token")
    private ResponseEntity<String> resendToken(@RequestParam("token") String token,
            HttpServletRequest request) {
        log.debug("REST request to resend verification token for user!");

        VerificationToken verificationToken = verificationTokenService.regenerateVerificationToken(token);

        eventPublisher.publishResendTokenEvent(verificationToken, request);

        return ResponseEntity.ok("/login?lang=" + request.getLocale().getLanguage());
    }

    // @PostMapping("/login")
    // public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {
    // String token = userService.login(loginDto);
    // return ResponseEntity.ok(token);
    // }
    //
    // @GetMapping("/{id}")
    // public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
    // User user = userService.getUserById(id);
    // return ResponseEntity.ok(user);
    // }
    //
    // @PutMapping("/{id}")
    // public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @Valid
    // @RequestBody UserDto userDto) {
    // User user = userService.updateUser(id, userDto);
    // return ResponseEntity.ok(user);
    // }
    //
    // @DeleteMapping("/{id}")
    // public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
    // userService.deleteUser(id);
    // return ResponseEntity.ok().build();
    // }
}
