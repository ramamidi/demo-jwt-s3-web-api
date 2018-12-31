package com.ramdemo.user.model;

public enum Role {
    ADMIN, USER;
    public String authority() {
        return "ROLE_" + this.name();
    }
}
