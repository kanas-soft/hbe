package com.kanas.hbe.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.kanas.hbe.domain.entity.Role;
import com.kanas.hbe.domain.enumeration.UserRole;
import com.kanas.hbe.repo.RoleRepository;
import com.kanas.hbe.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByRole(UserRole role) {
        return roleRepository.findByRole(role);
    }

    @Override
    public JpaRepository<Role, UUID> getJpaRepository() {
        return roleRepository;
    }

    @Override
    public Logger getLogger() {
        return null;
    }

    @Override
    public Class<Role> getType() {
        return Role.class;
    }

}
