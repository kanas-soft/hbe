package com.kanas.hbe.domain.entity;

import com.kanas.hbe.domain.enumeration.JobAdStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    private Handyman assignee;
}
