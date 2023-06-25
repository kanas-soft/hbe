package com.kanas.hbe.domain.enumeration;

public enum JobAdStatus {
    ACTIVE("ACTIVE"),
    ASSIGNED("ASSIGNED"),
    FINISHED("FINISHED");

    public final String status;

    JobAdStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
