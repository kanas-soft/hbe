package com.kanas.hbe.user.domain.entity;

import com.kanas.hbe.user.domain.entity.base.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact extends BaseEntity {

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String address;

    @Column
    private String phoneNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
