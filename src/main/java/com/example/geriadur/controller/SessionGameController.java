package com.example.geriadur.controller;

import com.example.geriadur.domain.consultation.Lexeme;
import com.example.geriadur.dto.LiteralTranslation;
import com.example.geriadur.service.game.SessionGameService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller()
@RequestMapping("/sessionGame")
public class SessionGameController {

    @Autowired
    private SessionGameService sessionGameService;

    @GetMapping("/{step}")
    public String sessionGameStep(@PathVariable("step") int gameStep, Model model) {
        model.addAttribute("score",sessionGameService.getScore());
        model.addAttribute("choices", sessionGameService.get5responseChoices(gameStep));
        model.addAttribute("etymonNames", sessionGameService.get15RandomEtymonName().get(gameStep));
        return "sessionGame";
    }
    @GetMapping("/{step}/")
    public String sessionGameConfirm(@PathVariable("step") int step, @ModelAttribute("response")LiteralTranslation literalTranslation) {
        sessionGameService.verifyResponse(step,literalTranslation);
        return "redirect:/sessionGame";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String closeSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/home";
    }
}
