package nl.hu.s3;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.SecurityContext;

import javax.crypto.SecretKey;
import java.io.IOException;

@Priority(Priorities.AUTHENTICATION)
public class AuthFilter implements ContainerRequestFilter {
    private static String secret = "Ditiseenhelegeheimestringenwevoegenwoordentoenetzolangtotdelibraryerblijmeeisasdjkfahsdkjfhasdkjhfaskdjhfalksdjhfaslkjdhfaskldjhfasdkljhfasdkljfh";


    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authHeader = requestContext.getHeaderString("Authorization");
        String username = getUserNameFromHeader(authHeader);

        if(username != null){
            SecurityContext ctx = new MySecurityContext(new UserWithoutPassword(username));
            requestContext.setSecurityContext(ctx);
        }
    }


    private String getUserNameFromHeader(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        String token = authHeader.substring("Bearer ".length());
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        Jws<Claims> claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseClaimsJws(token);

        String username = claims.getBody().getSubject();

        return username;
    }
}
