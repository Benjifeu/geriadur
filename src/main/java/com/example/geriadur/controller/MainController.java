package com.example.geriadur.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("")
    public String home() {
        return "home";
    }

    @ResponseBody
    @GetMapping("/admin")
    public String getUser(Model model) {
        return "Welcome admin";
    }

    @ResponseBody
    @GetMapping("/user")
    public String getAdmin(Model model) {
        return "Welcome user";
    }

    @RequestMapping("/sessionGameTest")
    public String sessionGame() {
        return "showJson";
    }
}
