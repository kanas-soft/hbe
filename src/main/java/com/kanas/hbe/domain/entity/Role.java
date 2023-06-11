package com.kanas.hbe.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import com.kanas.hbe.domain.enumeration.UserRole;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity {

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

}