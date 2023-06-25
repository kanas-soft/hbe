package com.kanas.fixtbe.UnitTests;

import com.kanas.fixtbe.domain.enumeration.UserRole;
import com.kanas.fixtbe.repository.RoleRepository;
import com.kanas.fixtbe.service.impl.RoleServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class RoleServiceUTests {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImpl roleService;

    @Nested
    @DisplayName("Retrieve Role UTests")
    class RetrieveRoleUTests {

        @Test
        void whenRetrievingRole_verifyRoleRepoIsCalled() {

            // Given When
            roleService.findByUserRole(UserRole.ADMIN);

            // Then
            verify(roleRepository, times(1)).findByUserRole(any());
        }

    }

}
