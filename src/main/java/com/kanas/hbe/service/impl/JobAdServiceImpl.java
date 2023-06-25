package com.kanas.hbe.service.impl;

import com.kanas.hbe.domain.entity.JobAd;
import com.kanas.hbe.repository.JobAdRepository;
import com.kanas.hbe.service.JobAdService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class JobAdServiceImpl implements JobAdService {

    private final JobAdRepository jobAdRepository;

    public JobAdServiceImpl(JobAdRepository jobAdRepository) {
        this.jobAdRepository = jobAdRepository;
    }

    @Override
    public JpaRepository<JobAd, UUID> getJpaRepository() {
        return jobAdRepository;
    }

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public Class<JobAd> getType() {
        return JobAd.class;
    }
}
