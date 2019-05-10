package ru.scrib.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/admin")
    public String adminLogin(){
        return "admin";
    }

    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }
}
