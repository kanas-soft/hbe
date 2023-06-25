package com.kanas.fixtbe.domain.entity;

import com.kanas.fixtbe.domain.enumeration.ReviewRating;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review extends BaseEntity {

    @Column
    @Enumerated
    private ReviewRating rating;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private LocalDateTime createdAt;

    @Column
    private Integer likes;

    @Column
    private Integer dislikes;

    @Column
    private Boolean testimonial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_client_id")
    private User client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_handyman_id")
    private Handyman handyman;
}
