package com.kanas.hbe.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kanas.hbe.domain.entity.Role;
import com.kanas.hbe.domain.enumeration.UserRole;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    Role findByRole(UserRole role);

}