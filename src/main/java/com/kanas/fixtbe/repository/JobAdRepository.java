package com.kanas.fixtbe.repository;

import com.kanas.fixtbe.domain.entity.JobAd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JobAdRepository extends JpaRepository<JobAd, UUID> {
}
