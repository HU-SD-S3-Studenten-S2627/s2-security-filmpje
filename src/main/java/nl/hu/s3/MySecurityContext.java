package nl.hu.s3;

import jakarta.ws.rs.core.SecurityContext;

import java.security.Principal;

public class MySecurityContext implements SecurityContext {
    private final UserWithoutPassword user;

    public MySecurityContext(UserWithoutPassword user){
        this.user = user;
    }

    @Override
    public Principal getUserPrincipal() {
        return this.user;
    }

    @Override
    public boolean isUserInRole(String role) {
        return true;
    }

    @Override
    public boolean isSecure() {
        return true;
    }

    @Override
    public String getAuthenticationScheme() {
        return "";
    }
}
