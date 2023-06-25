package com.kanas.fixtbe.service.impl;

import com.kanas.fixtbe.domain.entity.Region;
import com.kanas.fixtbe.repository.RegionRepository;
import com.kanas.fixtbe.service.RegionService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public JpaRepository<Region, UUID> getJpaRepository() {
        return regionRepository;
    }

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public Class<Region> getType() {
        return Region.class;
    }
}
