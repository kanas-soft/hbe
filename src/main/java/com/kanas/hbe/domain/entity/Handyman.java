package com.kanas.hbe.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
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

    @ManyToMany(mappedBy = "handymen")
    private Set<Expertise> expertises = new HashSet<>();

    @ManyToMany(mappedBy = "handymen")
    private Set<Region> regions = new HashSet<>();
}
