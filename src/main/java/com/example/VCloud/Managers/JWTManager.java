package com.example.VCloud.Managers;

public class JWTManager {
    private static final String SECRET_KEY = "test-secret-key";  // make hash ?

    public JWTManager() {

    }

    public String generateToken(String username) {
        return "";
    }

    public boolean verifyToken(String token) {
        return false;
    }

    public String getUsername(String token) {
        return "";
    }

    public String getEmail(String token) {
        return "";
    }
}
