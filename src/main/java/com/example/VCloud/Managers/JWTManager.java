package com.example.VCloud.Managers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

import java.util.HashMap;
import java.util.Map;

public class JWTManager {
    private static String SECRET_KEY;

    static {
        SECRET_KEY = generateSecretKey();
    }

    public JWTManager() {}

    public String generateToken(String login) {
        Map<String,Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(login)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))
                .signWith(SignatureAlgorithm.PS512, SECRET_KEY)
                .compact();
    }

    public boolean verifyToken(String token) {
        try{
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public String getLogin(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }


    private static String generateSecretKey() {
        SecureRandom secureRandom = new SecureRandom();

        byte[] key = new byte[32];
        secureRandom.nextBytes(key);

        return Base64.getEncoder().encodeToString(key);
    }
}
