package com.example.nutripath.Security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    private final String SECRET = "MySecretKeyForNutriPathProject123456789";
    
    private Key getSecretKey() {
        return new SecretKeySpec(SECRET.getBytes(),SignatureAlgorithm.HS256.getJcaName());
    }

    public String generateToken(String email,String role){
    Map<String,Object> claims=new HashMap<>();
    claims.put("role",role);
     return Jwts.builder()
        .claims(claims)
        .subject(email)
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis()+86400000))
        .signWith(getSecretKey())
        .compact();
}

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean validateToken(String token, String email) {
        return extractUsername(token).equals(email);
    }

    private Claims extractClaims(String token) {
    return Jwts.parser()
        .verifyWith((javax.crypto.SecretKey) getSecretKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
    }
}