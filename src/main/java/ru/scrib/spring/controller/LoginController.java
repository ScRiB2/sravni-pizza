package ru.scrib.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {
    @GetMapping("/login")
    public String showLoginPage(){
        return "login";
    }

    @GetMapping("/admin")
    public String showAdminPage(){
        return "admin";
    }
}
