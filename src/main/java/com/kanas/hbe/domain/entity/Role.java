package com.kanas.hbe.domain.entity;

import com.kanas.hbe.domain.entity.base.BaseEntity;
import com.kanas.hbe.domain.enumeration.UserRole;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Column
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public Role() {
    }

    public Role(UserRole role) {
        this.role = role;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
