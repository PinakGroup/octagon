package com.kids.crm.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority{
    TEACHER("ROLE_TEACHER"),
    STUDENT("ROLE_STUDENT"),
    ASSISTANT("ROLE_ASSISTANT"),
    SUPER_ADMIN("ROLE_SUPER_ADMIN");

    private String name;

    Role(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
