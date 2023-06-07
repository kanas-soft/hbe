package com.kanas.hbe.domain.enumeration;

public enum UserRole {
    ADMIN("ADMIN"),
    WORKER("WORKER"),
    CLIENT("CLIENT");

    public final String type;

    UserRole(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}