package co.edu.uniquindio.ProyectoAvanzada.seguridad;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;


@Component
public class JWTUtils {

    private static final String SECRET_KEY = "clave-super-secreta-para-firmar-el-jwt-que-debe-ser-muy-larga"; // mínimo 256 bits
    private static final long EXPIRATION_TIME = 86400000; // 1 día en milisegundos

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateToken(String id, Map<String, String> claims) {


        Instant now = Instant.now();


        return Jwts.builder()
                .claims(claims)
                .subject(id)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(1L, ChronoUnit.HOURS)))
                .signWith( getKey() )
                .compact();
    }


    public Jws<Claims> parseJwt(String jwtString) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, IllegalArgumentException {
        JwtParser jwtParser = Jwts.parser().verifyWith( getKey() ).build();
        return jwtParser.parseSignedClaims(jwtString);
    }

    public String getUsuarioIdFromToken(String token) {
        Claims claims = parseJwt(token).getBody();
        return claims.getSubject();
    }



    private SecretKey getKey(){
        String claveSecreta = "secretsecretsecretsecretsecretsecretsecretsecret";
        byte[] secretKeyBytes = claveSecreta.getBytes();
        return Keys.hmacShaKeyFor(secretKeyBytes);
    }






}
