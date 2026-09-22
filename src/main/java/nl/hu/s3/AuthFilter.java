package nl.hu.s3;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.sql.SQLException;

public class AuthFilter implements ContainerRequestFilter {
    private static String secret = "Ditiseenhelegeheimestringenwevoegenwoordentoenetzolangtotdelibraryerblijmeeisasdjkfahsdkjfhasdkjhfaskdjhfalksdjhfaslkjdhfaskldjhfasdkljhfasdkljfh";


    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authHeader = requestContext.getHeaderString("Authorization");
        checkHeader(authHeader);
    }


    private void checkHeader(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid credentials");
        }
        String token = authHeader.substring("Bearer ".length());
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        Jws<Claims> claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseClaimsJws(token);

        System.out.println(claims.getBody().getSubject());

    }
}
