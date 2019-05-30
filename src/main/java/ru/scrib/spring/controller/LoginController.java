package ru.scrib.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String showLogin(HttpServletRequest request, Model model) {
        Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
        if (map != null)
            model.addAttribute("newUser", map.get("newUser"));
        return "login";
    }
}
