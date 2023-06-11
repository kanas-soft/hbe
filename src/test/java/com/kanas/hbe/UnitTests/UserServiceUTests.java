package com.kanas.hbe.UnitTests;

import com.kanas.hbe.domain.entity.User;
import com.kanas.hbe.fixtures.UserFixtures;
import com.kanas.hbe.repository.RoleRepository;
import com.kanas.hbe.repository.UserRepository;
import com.kanas.hbe.service.RoleService;
import com.kanas.hbe.service.impl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class UserServiceUTests {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private RoleService roleService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Nested
    @DisplayName("Register User UTests")
    class RegisterUserUTests {

        @Test
        void whenCreating_verifyUserRepoIsCalled() throws Exception {

            // Given When
            userService.registerNewUserAccount(UserFixtures.createRegistrationDto());

            // Then
            verify(roleService, times(1)).findByUserRole(any());
            verify(userRepository, times(1)).save(any(User.class));
            verify(userRepository, times(1)).existsByEmail(any());
            verify(userRepository, times(1)).existsByUsername(any());
        }

    }

}
