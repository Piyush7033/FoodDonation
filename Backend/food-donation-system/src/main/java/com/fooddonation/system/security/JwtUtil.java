package com.fooddonation.system.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;




@Component
public class JwtUtil {



    private final String secret = "secretKeysecretKeysecretKey123456"; // must be long enough
    private final Key key = Keys.hmacShaKeyFor(secret.getBytes());

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key, SignatureAlgorithm.HS256) // ✅ new way
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()   // ✅ new way
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}