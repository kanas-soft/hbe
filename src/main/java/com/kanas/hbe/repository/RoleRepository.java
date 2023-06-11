package com.kanas.hbe.repository;

import com.kanas.hbe.domain.entity.Role;
import com.kanas.hbe.domain.enumeration.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    Role findByUserRole(UserRole userRole);

}