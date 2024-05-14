package com.example.geriadur.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SectionsController {

    @GetMapping("/sessionGame")
    public String launchSessionGame() {
        return "sessionGame";
    }

    @GetMapping("/wordstems/list")
    public String returnWordstemList() {
        return "wordstems-show";
    }

    @GetMapping("/lexique/entree")
    public String returnSpecificWordstemList() {
        return "wordstems-info";
    }

    @GetMapping("/noun/list")
    public String findNouns() {
        return "noun-show";
    }

    @GetMapping("/sources/list")
    public String findPaginatedSources() {
        return "sources-show";
    }

    @GetMapping("userprofile")
    public String showRegistrationForm() {
        return "user-profile";
    }

    @GetMapping("leaderboard")
    public String showLeaderboard() {
        return "leaderboard";
    }

    @GetMapping("")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @ResponseBody
    @GetMapping("/admin")
    public String getUser(Model model) {
        return "Welcome admin";
    }

}
