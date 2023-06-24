package com.kanas.hbe.repository;

import com.kanas.hbe.domain.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RegionRepository extends JpaRepository<Region, UUID> {

}
