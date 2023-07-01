package com.vti.shoppee.entity.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * implements GrantedAuthority: đang coi 1 đối tượng enum Role là 1 quyền trong Spring Security
 */

public enum Role implements GrantedAuthority {
    SELLER, CUSTOMER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
