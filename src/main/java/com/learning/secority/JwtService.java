package com.learning.secority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtService {

    public static final String SECRET = "mySecretKeyForJWTwhichIsAtLeast256BitsLong";


    //generating token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*30))
                .addClaims(new HashMap<>())
                .signWith(getSignedKey() , SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignedKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // Validating token
    public Claims varifySignatureAndExtractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignedKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // giving username
    public String extractUsername(String token){
        return varifySignatureAndExtractAllClaims(token).getSubject();
    }

    //giving expire data
    public Date getExpireDate(String token){
        return varifySignatureAndExtractAllClaims(token).getExpiration();
    }

    public boolean isTokenExpire(String token) {
        return getExpireDate(token).before(new Date());
    }
}
