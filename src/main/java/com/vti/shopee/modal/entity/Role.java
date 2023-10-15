package com.vti.shopee.modal.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    CUSTOMER, SELLER, ADMIN;

    @Override
    public String getAuthority() {
        return null;
    }
}
