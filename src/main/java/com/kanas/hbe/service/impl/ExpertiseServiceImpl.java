package com.kanas.hbe.service.impl;

import com.kanas.hbe.domain.entity.Expertise;
import com.kanas.hbe.repository.ExpertiseRepository;
import com.kanas.hbe.service.ExpertiseService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class ExpertiseServiceImpl implements ExpertiseService {

    private final ExpertiseRepository expertiseRepository;

    public ExpertiseServiceImpl(ExpertiseRepository expertiseRepository) {
        this.expertiseRepository = expertiseRepository;
    }

    @Override
    public JpaRepository<Expertise, UUID> getJpaRepository() {
        return expertiseRepository;
    }

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public Class<Expertise> getType() {
        return Expertise.class;
    }
}
