package com.kanas.fixtbe.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "handymen")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Handyman extends BaseEntity {

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "id")
    private User user;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String phoneNumber;

    @Column
    private String company;

    @OneToMany(
            mappedBy = "assignee",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<JobAd> assignedJobs = new ArrayList<>();

    @OneToMany(
            mappedBy = "handyman",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(mappedBy = "handymen")
    private Set<Expertise> expertises = new HashSet<>();

    @ManyToMany(mappedBy = "handymen")
    private Set<Region> regions = new HashSet<>();
}
