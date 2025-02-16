package com.blws.side.auth.enumerate;

import lombok.Getter;

@Getter
public enum AccountStatus {
    SLEEPER("S"),
    BLOCKED("B");

    private final String alias;

    AccountStatus(String alias) {
        this.alias = alias;
    }

    public static AccountStatus fromAlias(String alias) {
        for (AccountStatus status : AccountStatus.values()) {
            if (status.getAlias().equalsIgnoreCase(alias)) {
                return status;
            }
        }
        return null;
    }
}
