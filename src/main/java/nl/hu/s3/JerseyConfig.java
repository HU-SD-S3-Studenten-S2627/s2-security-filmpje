package nl.hu.s3;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.springframework.context.annotation.Configuration;

@ApplicationPath("/api")
@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {

        register(DemoResource.class);
        register(LoginResource.class);
        register(AuthFilter.class);
        register(RolesAllowedDynamicFeature.class);
    }
}
