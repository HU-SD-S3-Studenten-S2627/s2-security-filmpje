package nl.hu.s3;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;

import java.sql.SQLException;

@Resource
@Path("demo")
public class DemoResource {

    @Path("public")
    @POST
    @Consumes("application/json")
    @PermitAll
    public String doPublic(SomeDTO dto) {
        return "public" + dto.message;
    }


    @Path("private")
    @GET
    @Consumes("application/json")
    @RolesAllowed("user")
    public String getPrivate() throws SQLException {

        return "Dit is prive!";
    }


    @Path("private")
    @POST
    @Consumes("application/json")
    @RolesAllowed("user")
    public String doPrivate(SomeDTO dto) throws SQLException {
        return "private" + dto.message;
    }

    @Path("admin")
    @POST
    @Consumes("application/json")
    @RolesAllowed("admin")
    public String doAdmin(SomeDTO dto) {
        return "private" + dto.message;
    }
}
