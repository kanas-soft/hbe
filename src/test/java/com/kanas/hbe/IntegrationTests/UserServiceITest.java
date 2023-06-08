package com.kanas.hbe.IntegrationTests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import com.kanas.hbe.domain.dto.RegistrationDto;
import com.kanas.hbe.exception.EmailAlreadyExistsException;
import com.kanas.hbe.exception.UsernameAlreadyExistsException;
import com.kanas.hbe.fixtures.UserFixtures;
import com.kanas.hbe.repo.RoleRepository;
import com.kanas.hbe.repo.UserRepository;
import com.kanas.hbe.service.RoleService;
import com.kanas.hbe.service.UserService;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceITest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Nested
    @DisplayName("Register User ITests")
    class RegisterUserITests {

        @Test
        void whenCreating_throwExistingEmailError() throws Exception {

            // Given
            RegistrationDto registrationDto = UserFixtures.createExistingEmailRegistrationDto();
            // When Then
            assertThrows(EmailAlreadyExistsException.class,
                    () -> userService.registerNewUserAccount(registrationDto));
        }

        @Test
        void whenCreating_throwExistingUsernameError() throws Exception {

            // Given
            RegistrationDto registrationDto = UserFixtures.createExistingUsernameRegistrationDto();
            // When Then
            assertThrows(UsernameAlreadyExistsException.class,
                    () -> userService.registerNewUserAccount(registrationDto));
        }

        @Test
        void whenCreating_throwNoError() throws Exception {

            // Given
            RegistrationDto registrationDto = UserFixtures.createRegistrationDto();
            // When Then
            assertDoesNotThrow(() -> userService.registerNewUserAccount(registrationDto));
        }

    }

}
