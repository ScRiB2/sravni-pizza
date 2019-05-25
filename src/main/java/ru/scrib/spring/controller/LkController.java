package ru.scrib.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LkController {
    @GetMapping("/lk")
    public String showLkPage(){
        return "lk";
    }

    @GetMapping("/admin")
    public String showAdminPage(){
        return "admin";
    }
}

