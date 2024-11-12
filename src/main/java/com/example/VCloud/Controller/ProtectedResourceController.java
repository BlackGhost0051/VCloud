package com.example.VCloud.Controller;

import com.example.VCloud.Model.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProtectedResourceController {
/*
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/protected")
    public ResponseEntity<String> protectedEndpoint(@RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);
            if (jwtUtil.validateToken(jwtToken)) {

                String username = jwtUtil.extractUsername(jwtToken);
                return ResponseEntity.ok("Welcome, " + username + "!");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
    }
*/
}

