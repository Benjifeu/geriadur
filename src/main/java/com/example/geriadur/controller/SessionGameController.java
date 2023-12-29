package com.example.geriadur.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SessionGameController {

    @GetMapping("/sessionGame")
    public String launchSessionGame() {
        return "sessionGame";
    }

}
