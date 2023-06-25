package com.kanas.fixtbe.domain.enumeration;

public enum UserRole {
    ADMIN("ADMIN"),
    HANDYMAN("HANDYMAN"),
    CLIENT("CLIENT");

    public final String type;

    UserRole(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}