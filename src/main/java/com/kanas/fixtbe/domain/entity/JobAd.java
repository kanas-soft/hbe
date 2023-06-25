package com.kanas.fixtbe.domain.entity;

import com.kanas.fixtbe.domain.enumeration.JobAdStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "job_ads")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobAd extends BaseEntity {

    @Column
    private String description;

    @Column
    private String location;

    @Column
    private Double price;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime finishedAt;

    @Column
    @Enumerated(value = EnumType.STRING)
    private JobAdStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private User client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    private Handyman assignee;

    @ManyToMany(mappedBy = "jobAds")
    private Set<Expertise> expertises = new HashSet<>();
}
