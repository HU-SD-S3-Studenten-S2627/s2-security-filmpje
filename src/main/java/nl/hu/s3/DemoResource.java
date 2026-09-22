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
    public String getPrivate() throws SQLException {

        return "Dit is prive!";
    }


    @Path("private")
    @POST
    @Consumes("application/json")
    public String doPrivate(SomeDTO dto) throws SQLException {
        return "private" + dto.message;
    }

    @Path("admin")
    @POST
    @Consumes("application/json")
    public String doAdmin(SomeDTO dto) {
        return "private" + dto.message;
    }
}
