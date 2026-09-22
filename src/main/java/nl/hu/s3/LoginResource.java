package nl.hu.s3;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.Resource;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import javax.crypto.SecretKey;
import java.sql.SQLException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Resource
@Path("login")
public class LoginResource {
    private static String secret = "Ditiseenhelegeheimestringenwevoegenwoordentoenetzolangtotdelibraryerblijmeeisasdjkfahsdkjfhasdkjhfaskdjhfalksdjhfaslkjdhfaskldjhfasdkljhfasdkljfh";

    @POST
    public String login(LoginDTO dto) throws SQLException {
        String username = dto.username;
        String password = dto.password;
        UserDAO userDAO = new UserDAO();
        User postedUser = userDAO.findUser(username);
        if (postedUser == null || !postedUser.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }

        byte[] keyBytes = Decoders.BASE64.decode(secret);
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        String jwtToken = Jwts.builder()
                .issuer("HU-Tom-Demo")
                .signWith(key)
                .expiration(Date.from(Instant.now().plus(1, ChronoUnit.DAYS)))
                .subject("test").compact();

        return jwtToken;
    }


}
