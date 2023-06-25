package com.kanas.fixtbe.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "regions")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Region extends BaseEntity {

    @Column
    private String city;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "handymen_regions",
            joinColumns = @JoinColumn(name = "handyman_id"),
            inverseJoinColumns = @JoinColumn(name = "region_id")
    )
    private Set<Handyman> handymen = new HashSet<>();
}
