package com.example.VCloud.Controller;

import com.example.VCloud.Managers.DataBaseManager;
import com.example.VCloud.Managers.FileManager;
import com.example.VCloud.Managers.JWTManager;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Controller
public class GlobalController {



    @GetMapping("/")
    public ModelAndView index(HttpServletResponse response) {

        // get jwt
        // if jwt true
        // return index ?? find userDir and return his folders
        // else return login

        return make_error(response,500,"Something went wrong.");
    }

    @GetMapping("/login")
    public ModelAndView login(HttpServletResponse response) {
        try{
            return new ModelAndView("login");
        } catch (Exception e){
            return make_error(response, 400, "Something went wrong.\n" + e.toString());
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity login(@RequestParam String login,@RequestParam String password,HttpServletResponse response){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA3-512");
            byte[] hashedBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            String hashedPassword = Base64.getEncoder().encodeToString(hashedBytes);

            DataBaseManager db = new DataBaseManager();

            if(db.checkUser(login,hashedPassword)){
                JWTManager jwtManager = new JWTManager();
                String jwtTocken = jwtManager.generateToken(login);
                Cookie cookie = new Cookie("jwt_token", jwtTocken);
                cookie.setPath("/");
                cookie.setSecure(true);
                cookie.setHttpOnly(true);

                response.addCookie(cookie);

                return ResponseEntity.ok().body(jwtTocken);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login credentials");
            }
        } catch (NoSuchAlgorithmException e){
            throw new RuntimeException("SHA3-512 algorithm not available", e);
        }
    }

    @GetMapping("/register")
    public ModelAndView register(HttpServletResponse response) {
        try{
            return new ModelAndView("register");
        } catch (Exception e){
            return make_error(response, 400, "Something went wrong.\n" + e.toString());
        }
    }

    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestParam String email, @RequestParam String login, @RequestParam String password){
        try{
            if (!isValidEmail(email)) {
                return "Invalid email address!";
            }

            MessageDigest digest = MessageDigest.getInstance("SHA3-512");
            byte[] hashedBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            String hashedPassword = Base64.getEncoder().encodeToString(hashedBytes);

            DataBaseManager db = new DataBaseManager();

            return db.registerUser(email,login,hashedPassword);
        } catch (NoSuchAlgorithmException e){
            throw new RuntimeException("SHA3-512 algorithm not available", e);
        }
    }
/*
    @GetMapping("/get_dir_tree")
    @ResponseBody
    public String getDirectoryTree(@RequestParam String login) {
        FileManager fileManager = new FileManager();
        JSONObject directoryTree = fileManager.getDirectoryTree(login);
        if (directoryTree != null) {
            return directoryTree.toString();
        }
        return "{\"error\": \"User directory not found or an error occurred.\"}";
    }
*/

    @GetMapping("/test")
    @ResponseBody
    public String db_test(){
        JWTManager jwtManager = new JWTManager();
        JWTManager jwtManager2 = new JWTManager();
        JWTManager jwtManager3 = new JWTManager();
        return "Database initialization successful!";
    }

    ModelAndView make_error(HttpServletResponse response, int status, String error){
        response.setStatus(status);
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("status", status);
        modelAndView.addObject("error", error);
        return modelAndView;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return email != null && email.matches(emailRegex);
    }
}
