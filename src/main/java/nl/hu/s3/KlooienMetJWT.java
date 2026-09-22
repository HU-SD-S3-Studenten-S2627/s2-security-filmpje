package nl.hu.s3;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class KlooienMetJWT {

    private static String secret = "Ditiseenhelegeheimestringenwevoegenwoordentoenetzolangtotdelibraryerblijmeeisasdjkfahsdkjfhasdkjhfaskdjhfalksdjhfaslkjdhfaskldjhfasdkljhfasdkljfh";
    static void main() {

        byte[] keyBytes = Decoders.BASE64.decode(secret);
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        String bla = Jwts.builder()
                .issuer("HU-Tom-Demo")
                .signWith(key)
                .expiration(Date.from(Instant.now().plus(1, ChronoUnit.DAYS)))
                .subject("test").compact();

        System.out.println(bla);

        Jws<Claims> claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseClaimsJws(bla);

        System.out.println(claims.getBody().getSubject());


    }
}
