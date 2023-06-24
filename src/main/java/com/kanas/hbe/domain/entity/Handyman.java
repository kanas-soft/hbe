package com.kanas.hbe.domain.entity;

import jakarta.persistence.*;
import lombok.*;

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

}
