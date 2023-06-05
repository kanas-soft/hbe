package com.kanas.hbe.domain.enumeration;

public enum UserRole {
    ADMIN("ADMIN"),
    PET_OWNER("PET_OWNER"),
    PET_CARETAKER("PET_CARETAKER");

    public final String type;

    UserRole(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
