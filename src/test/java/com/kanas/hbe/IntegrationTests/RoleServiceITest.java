package com.kanas.hbe.IntegrationTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import com.kanas.hbe.domain.enumeration.UserRole;
import com.kanas.hbe.repo.RoleRepository;
import com.kanas.hbe.repo.UserRepository;
import com.kanas.hbe.service.RoleService;
import com.kanas.hbe.service.impl.UserServiceImpl;

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
        void whenLoadingByUsername_throwNoError() throws Exception {

            // Given
            // When
            var admin = roleService.findByUserRole(UserRole.ADMIN);
            var worker = roleService.findByUserRole(UserRole.WORKER);
            var client = roleService.findByUserRole(UserRole.CLIENT);

            // Then
            assertNotNull(admin);
            assertNotNull(worker);
            assertNotNull(client);
            assertEquals(UserRole.ADMIN, admin.getUserRole());
            assertEquals(UserRole.WORKER, worker.getUserRole());
            assertEquals(UserRole.CLIENT, client.getUserRole());

        }
    }

    @Nested
    @DisplayName("Implementet Role ITests")
    class ImplementedRoleITests {

        @Test
        void getJpaRepository_noErrorsExpected() throws Exception {

            // Given
            // When
            var roleRepo = roleService.getJpaRepository();

            // Then
            assertNotNull(roleRepo);
            assertEquals(roleRepository, roleRepo);

        }

        @Test
        void getLogger_noErrorExpected() throws Exception {

            // Given
            // When
            var logger = roleService.getLogger();
            // Then
            assertNotNull(logger);
            assertEquals("com.kanas.hbe.service.impl.RoleServiceImpl", logger.getName());
        }

        @Test
        void getType_noErrorExpected() throws Exception {

            // Given
            // When
            var roleType = roleService.getType();
            // Then
            assertNotNull(roleType);
            assertEquals("class com.kanas.hbe.domain.entity.Role", roleType.toString());
        }
    }

}
