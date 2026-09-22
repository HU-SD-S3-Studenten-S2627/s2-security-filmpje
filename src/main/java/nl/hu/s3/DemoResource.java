package nl.hu.s3;

import jakarta.annotation.Resource;
import jakarta.ws.rs.*;

import java.sql.SQLException;

@Resource
@Path("demo")
public class DemoResource {

    @Path("public")
    @POST
    @Consumes("application/json")
    public String doPublic(SomeDTO dto) {
        return "public" + dto.message;
    }


    @Path("private")
    @GET
    @Consumes("application/json")
    public String getPrivate(@HeaderParam("Authorization") String authHeader) throws SQLException {
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
        return "Dit is prive!" + authHeader;
    }


    @Path("private")
    @POST
    @Consumes("application/json")
    public String doPrivate(@HeaderParam("Authorization") String authHeader, SomeDTO dto) throws SQLException {
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

        return "private" + dto.message;
    }

    @Path("admin")
    @POST
    @Consumes("application/json")
    public String doAdmin(SomeDTO dto) {
        return "private" + dto.message;
    }
}
