package com.example.geriadur.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SectionsController {

    @GetMapping("/sessionGame")
    public String launchSessionGame() {
        return "sessionGame";
    }
    
    @GetMapping("/wordstems/list")
    public String findPaginated() {
        return "wordstems-show";
    }

    @GetMapping("/sources/list")
    public String findPaginatedSources() {
        return "sources-show";
    }

    @GetMapping("userprofile")
    public String showRegistrationForm() {
        return "user-profile";
    }
}
