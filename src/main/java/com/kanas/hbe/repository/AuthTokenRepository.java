package com.kanas.hbe.repository;

import com.kanas.hbe.domain.entity.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthTokenRepository extends JpaRepository<AuthToken, Long> {
}
