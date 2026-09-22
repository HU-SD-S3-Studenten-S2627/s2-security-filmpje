package nl.hu.s3;

import java.security.Principal;

public class UserWithoutPassword implements Principal {
    private String username;

    public UserWithoutPassword(String username) {
        this.username = username;
    }

    @Override
    public String getName() {
        return this.username;
    }
}
