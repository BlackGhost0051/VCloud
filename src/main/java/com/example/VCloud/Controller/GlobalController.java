package com.example.VCloud.Controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GlobalController {

    @GetMapping("/")
    public ModelAndView index(HttpServletResponse response) {

        // get jwt
        // if jwt true
        // return index
        // else return login

        response.setStatus(500);
        return make_error(response,500,"Something went wrong");
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,String password){
        return "error";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }


    ModelAndView make_error(HttpServletResponse response, int error_code, String error_message){
        response.setStatus(error_code);
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error_code", error_code);
        modelAndView.addObject("error_message", error_message);
        return modelAndView;
    }
}
