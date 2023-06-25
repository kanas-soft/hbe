package com.kanas.fixtbe.IntegrationTests;

import com.kanas.fixtbe.domain.dto.RegistrationDto;
import com.kanas.fixtbe.exception.ConfirmationTokenExpiredException;
import com.kanas.fixtbe.exception.EmailAlreadyExistsException;
import com.kanas.fixtbe.exception.InvalidConfirmationTokenException;
import com.kanas.fixtbe.exception.UsernameAlreadyExistsException;
import com.kanas.fixtbe.fixtures.UserFixtures;
import com.kanas.fixtbe.repository.RoleRepository;
import com.kanas.fixtbe.repository.UserRepository;
import com.kanas.fixtbe.service.RoleService;
import com.kanas.fixtbe.service.impl.UserServiceImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.time.Clock;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
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
    private UserServiceImpl userService;

    @Autowired
    private Clock clock;

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

    @Nested
    @DisplayName("Confirm Registration Of User ITests")
    class ConfirmRegistrationUserITests {

        @Test
        void whenConfirming_throwInvalidTokenException() throws Exception {
            // Given
            String invalidToken = UUID.randomUUID().toString();
            // When Then
            assertThrows(InvalidConfirmationTokenException.class,
                    () -> userService.confirmRegistration(invalidToken));
        }

        @Test
        void whenConfirming_throwInvalidTokenExpiryDateException() throws Exception {

            // Given
            String invalidToken = "8ce266f2-18c7-4c61-892d-04f54a929c8f";
            // When Then
            assertThrows(ConfirmationTokenExpiredException.class,
                    () -> userService.confirmRegistration(invalidToken));
        }

        @Test
        @Transactional
        void whenConfirming_NoErrors() throws Exception {

            // Given
            String validToken = "f5fb51a1-b43d-46d7-9d91-9260d52d2b7a";
            // When Then
            assertDoesNotThrow(() -> userService.confirmRegistration(validToken));
        }

    }

    @Nested
    @DisplayName("Load User ITests")
    class LoadUserITests {

        @Test
        void whenLoadingByUsername_throwNoError() throws Exception {

            // Given
            // When
            var user = userService.loadUserByUsername("tosho");

            // Then
            assertNotNull(user);
            assertEquals("tosho", user.getUsername());
        }

        @Test
        void whenLoadingByUsername_throwError() throws Exception {

            // Given
            // When
            // Then
            assertThrows(UsernameNotFoundException.class,
                    () -> userService.loadUserByUsername("test"));
        }
    }

    @Nested
    @DisplayName("Implemented User ITests")
    class ImplementedUserITests {

        @Test
        void getJpaRepository_noErrorsExpected() throws Exception {

            // Given
            // When
            var userRepo = userService.getJpaRepository();

            // Then
            assertNotNull(userRepo);
            assertEquals(userRepository, userRepo);

        }

        @Test
        void getLogger_noErrorExpected() throws Exception {

            // Given
            // When
            var logger = userService.getLogger();
            // Then
            assertNotNull(logger);
            assertEquals("com.kanas.fixtbe.service.impl.UserServiceImpl", logger.getName());
        }

        @Test
        void getType_noErrorExpected() throws Exception {

            // Given
            // When
            var userType = userService.getType();
            // Then
            assertNotNull(userType);
            assertEquals("class com.kanas.fixtbe.domain.entity.User", userType.toString());
        }
    }

}
