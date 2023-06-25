package com.kanas.fixtbe.IntegrationTests;

import com.kanas.fixtbe.domain.enumeration.UserRole;
import com.kanas.fixtbe.repository.RoleRepository;
import com.kanas.fixtbe.repository.UserRepository;
import com.kanas.fixtbe.service.RoleService;
import com.kanas.fixtbe.service.impl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class RoleServiceITest {

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

    @Nested
    @DisplayName("Load Role ITests")
    class LoadRoleITests {

        @Test
        void whenLoadingByUsername_throwNoError() {

            // Given
            // When
            var admin = roleService.findByUserRole(UserRole.ADMIN);
            var worker = roleService.findByUserRole(UserRole.HANDYMAN);
            var client = roleService.findByUserRole(UserRole.CLIENT);

            // Then
            assertNotNull(admin);
            assertNotNull(worker);
            assertNotNull(client);
            assertEquals(UserRole.ADMIN, admin.getUserRole());
            assertEquals(UserRole.HANDYMAN, worker.getUserRole());
            assertEquals(UserRole.CLIENT, client.getUserRole());

        }
    }

    @Nested
    @DisplayName("Implemented Role ITests")
    class ImplementedRoleITests {

        @Test
        void getJpaRepository_noErrorsExpected() {

            // Given
            // When
            var roleRepo = roleService.getJpaRepository();

            // Then
            assertNotNull(roleRepo);
            assertEquals(roleRepository, roleRepo);

        }

        @Test
        void getLogger_noErrorExpected() {

            // Given
            // When
            var logger = roleService.getLogger();
            // Then
            assertNotNull(logger);
            assertEquals("com.kanas.fixtbe.service.impl.RoleServiceImpl", logger.getName());
        }

        @Test
        void getType_noErrorExpected() {

            // Given
            // When
            var roleType = roleService.getType();
            // Then
            assertNotNull(roleType);
            assertEquals("class com.kanas.fixtbe.domain.entity.Role", roleType.toString());
        }
    }

}
