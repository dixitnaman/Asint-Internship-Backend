package com.ratingmanagement.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.jackson.io.JacksonDeserializer;
import io.jsonwebtoken.jackson.io.JacksonSerializer;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "mysupersecretkeyformilitaryonlyuse123456";
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 5; // 5 hours

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(String subject) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .json(new JacksonSerializer<>())
                .subject(subject)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .claims(claims)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            parseToken(token); // Throws exception if invalid
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        return parseToken(token).getPayload().getSubject();
    }

    private Jwt<Header, Claims> parseToken(String token) {
        return Jwts.parser()
                .json(new JacksonDeserializer<>())
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token);
    }
}
