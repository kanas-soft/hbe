package com.kanas.hbe.service.impl;

import com.kanas.hbe.domain.entity.Review;
import com.kanas.hbe.repository.ReviewRepository;
import com.kanas.hbe.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public JpaRepository<Review, UUID> getJpaRepository() {
        return reviewRepository;
    }

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public Class<Review> getType() {
        return Review.class;
    }
}
