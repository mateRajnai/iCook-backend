package com.coodcool.icook.security;

import com.coodcool.icook.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.util.Assert;

public class RoleEnumGrantedAuthority implements GrantedAuthority {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final String role;

    public RoleEnumGrantedAuthority(Role role) {
        Assert.hasText(role.getName(), "A granted authority textual representation is required");
        this.role = role.getName();
    }


    @Override
    public String getAuthority() {
        return role;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof RoleEnumGrantedAuthority) {
            return role.equals(((RoleEnumGrantedAuthority) obj).role);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this.role.hashCode();
    }

    @Override
    public String toString() {
        return this.role;
    }

}
