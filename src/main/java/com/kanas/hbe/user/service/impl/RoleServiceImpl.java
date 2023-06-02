package com.kanas.hbe.user.service.impl;

import com.kanas.hbe.user.domain.entity.Role;
import com.kanas.hbe.user.repository.RoleRepository;
import com.kanas.hbe.user.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public JpaRepository<Role, Long> getJpaRepository() {
        return roleRepository;
    }

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public Class<Role> getType() {
        return Role.class;
    }
}
