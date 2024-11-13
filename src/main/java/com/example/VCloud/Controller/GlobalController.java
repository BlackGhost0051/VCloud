package com.example.VCloud.Controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public String login(@RequestParam String username, String password){
        // pass make hash
        // find in database
        // return jwt
        return username + " " + password;
    }

    @GetMapping("/register")
    public ModelAndView register(HttpServletResponse response) {
        try{
            return new ModelAndView("register");
        } catch (Exception e){
            return make_error(response, 400, "Something went wrong.\n" + e.toString());
        }
    }


    ModelAndView make_error(HttpServletResponse response, int error_code, String error_message){
        response.setStatus(error_code);
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error_code", error_code);
        modelAndView.addObject("error_message", error_message);
        return modelAndView;
    }
}
