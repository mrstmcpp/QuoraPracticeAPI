package org.mrstm.quoraapi.utils;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtils {
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long expiration;

    private SecretKey key;

    //intializing key & used postconstruct to avoid repeated creation of the key
    @PostConstruct //Executes after dependency injection.
    public void init(){
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    //token generation
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(key)
                .expiration(new Date((new Date()).getTime() + expiration))
                .compact();
    }

    //getting username from token
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(key).build()
                .parseSignedClaims(token).getPayload().getSubject();
    }

    //validate token
    public Boolean validateToken(String token) {
        try{
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        }catch (SecurityException e){
            System.out.println("Invalid token");
        }
        return false;
    }
}
