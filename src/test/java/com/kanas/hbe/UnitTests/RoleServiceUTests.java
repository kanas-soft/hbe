package com.kanas.hbe.UnitTests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.kanas.hbe.domain.enumeration.UserRole;
import com.kanas.hbe.repo.RoleRepository;
import com.kanas.hbe.service.impl.RoleServiceImpl;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class RoleServiceUTests {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImpl roleService;

    @Nested
    @DisplayName("Retreive Role UTests")
    class RetreiveRoleUTests {

        @Test
        void whenRetreivingRole_verifyRoleRepoIsCalled() throws Exception {

            // Given When
            roleService.findByUserRole(UserRole.ADMIN);

            // Then
            verify(roleRepository, times(1)).findByUserRole(any());
        }

    }

}
