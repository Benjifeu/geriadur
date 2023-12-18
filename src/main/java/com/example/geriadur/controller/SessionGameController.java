package com.example.geriadur.controller;

import com.example.geriadur.service.game.SessionGameService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("sessionGame")
public class SessionGameController {

    @Autowired
    private SessionGameService sessionGameService;

    @GetMapping("/{semFieldType}")
    public String lauchingSessionGame(@PathVariable("semFieldType") int semFieldType, Model model) {
        model.addAttribute("responses", sessionGameService.getAllLiteralTranslationBySemField(semFieldType, 1));
        model.addAttribute("etymonNames", sessionGameService.get15RandomEtymonName(semFieldType));

        return "sessionGame";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String closeSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/home";
    }
}
