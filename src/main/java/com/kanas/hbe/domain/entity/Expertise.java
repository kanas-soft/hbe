package com.kanas.hbe.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "expertises")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Expertise extends BaseEntity {

    @Column
    private String service;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "handymen_expertises",
            joinColumns = @JoinColumn(name = "handyman_id"),
            inverseJoinColumns = @JoinColumn(name = "expertise_id")
    )
    private Set<Handyman> handymen = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "job_ads_expertises",
            joinColumns = @JoinColumn(name = "job_ad_id"),
            inverseJoinColumns = @JoinColumn(name = "expertise_id")
    )
    private Set<JobAd> jobAds = new HashSet<>();
}
