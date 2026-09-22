package nl.hu.s3;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;

import java.io.IOException;
import java.sql.SQLException;

public class AuthFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String authHeader = requestContext.getHeaderString("Authorization");
        try {
            checkHeader(authHeader);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void checkHeader(String authHeader) throws SQLException {
        if(authHeader == null || !authHeader.startsWith("Basic ")){
            throw new RuntimeException("Invalid credentials");
        }
        String header = authHeader.substring("Basic ".length());
        String[] split = header.split(":");
        String username = split[0];
        String password = split[1];
        UserDAO userDAO = new UserDAO();
        User postedUser = userDAO.findUser(username);
        if (postedUser == null || !postedUser.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }
    }
}
