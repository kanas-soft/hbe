package com.kanas.hbe.service.impl;

import com.kanas.hbe.domain.entity.Region;
import com.kanas.hbe.repository.RegionRepository;
import com.kanas.hbe.service.RegionService;
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
