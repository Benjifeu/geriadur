package com.example.geriadur.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SessionGameController {

    @GetMapping("/sessionGame")
    public String launchSessionGame() {
        return "sessionGame";
    }

}
