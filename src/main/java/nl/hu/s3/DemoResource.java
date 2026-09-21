package nl.hu.s3;

import jakarta.annotation.Resource;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

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
    @POST
    @Consumes("application/json")
    public String doPrivate(SomeDTO dto) {
        return "private" + dto.message;
    }

    @Path("admin")
    @POST
    @Consumes("application/json")
    public String doAdmin(SomeDTO dto) {
        return "private" + dto.message;
    }
}
