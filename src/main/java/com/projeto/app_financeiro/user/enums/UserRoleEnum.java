package com.projeto.app_financeiro.user.enums;

public enum UserRoleEnum {

    USER("ROLE_USER");

    private String role;

    private UserRoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
