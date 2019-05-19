package ru.scrib.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LkController {
    @GetMapping("/lk")
    public String showLkPage(Model model){
        return "lk";
    }

    @GetMapping("/admin")
    public String showAdminPage(){
        return "admin";
    }
}

