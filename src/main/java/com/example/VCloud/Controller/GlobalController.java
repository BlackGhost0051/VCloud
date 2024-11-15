package com.example.VCloud.Controller;

import jakarta.servlet.http.HttpServletResponse;
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
    public String login(@RequestParam String username,@RequestParam String password){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA3-512");
            byte[] hashedBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            String hashedPassword = Base64.getEncoder().encodeToString(hashedBytes);
            return "Username: " + username + ", Hashed Password: " + hashedPassword;
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
    public String register(@RequestParam String email, @RequestParam String username, @RequestParam String password){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA3-512");
            byte[] hashedBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            String hashedPassword = Base64.getEncoder().encodeToString(hashedBytes);

            // verify is email?

            // connect to database

            return "Username: " + username + ", Hashed Password: " + hashedPassword;
        } catch (NoSuchAlgorithmException e){
            throw new RuntimeException("SHA3-512 algorithm not available", e);
        }
    }

    ModelAndView make_error(HttpServletResponse response, int status, String error){
        response.setStatus(status);
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("status", status);
        modelAndView.addObject("error", error);
        return modelAndView;
    }
}
