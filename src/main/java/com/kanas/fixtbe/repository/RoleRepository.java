package com.kanas.fixtbe.repository;

import com.kanas.fixtbe.domain.entity.Role;
import com.kanas.fixtbe.domain.enumeration.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    Role findByUserRole(UserRole userRole);

}