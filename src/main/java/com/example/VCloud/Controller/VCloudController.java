package com.example.VCloud.Controller;

import com.example.VCloud.Model.JwtUtil;
import com.example.VCloud.Model.User;
import com.example.VCloud.Model.Users;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@Controller
public class VCloudController {
    @Autowired
    private Users users;
    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hi";
    }

    @GetMapping("/users")
    @ResponseBody
    public List<User> getAllUsers() {
        return users.getAllUsers();
    }



    /*@PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody MultiValueMap<String, String> loginData) {
        String login = loginData.getFirst("login");
        String password = loginData.getFirst("password");

        List<User> userList = users.getAllUsers();

        for (User user : userList) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return "Successful login for user: " + login;
            }
        }
        return "Invalid login credentials";
    }*/

//    @PostMapping("/login")
//    @ResponseBody
//    public ResponseEntity<?> login(@RequestBody MultiValueMap<String, String> loginData) {
//        String login = loginData.getFirst("login");
//        String password = loginData.getFirst("password");
//
//        List<User> userList = users.getAllUsers();
//
//        for (User user : userList) {
//            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
//                return ResponseEntity.ok().body("Successful login for user: " + login);
//            }
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login credentials");
//    }


        @Autowired
        private JwtUtil jwtUtil;

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody MultiValueMap<String, String> loginData, HttpServletResponse response) {
        String login = loginData.getFirst("login");
        String password = loginData.getFirst("password");

        List<User> userList = users.getAllUsers();

        for (User user : userList) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                System.out.println("GOOD");
                String token = jwtUtil.generateToken(login);

                Cookie cookie = new Cookie("jwt_token", token);
                cookie.setPath("/");
                cookie.setSecure(true);
                cookie.setHttpOnly(true);

                response.addCookie(cookie);

                return ResponseEntity.ok().body(token);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login credentials");
    }

}
