package com.example.geriadur.controller;

import com.example.geriadur.domain.LiteralTranslation;
import com.example.geriadur.dto.GameSessionStep;
import com.example.geriadur.repositories.UserRepository;
import com.example.geriadur.service.game.SessionGameService;
import com.example.geriadur.service.user.UserService;
import com.example.geriadur.service.user.UserServiceImpl;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class SessionGameController {

    @Autowired
    private SessionGameService sessionGameService;
    @Autowired
    private UserServiceImpl userService;



    @RequestMapping("/sessionGame/get")
    public String sessionGameStep(Model model) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        System.out.println(sessionGameService.get15GameSessionStep(1));
        String json = om.writeValueAsString(sessionGameService.get15GameSessionStep(1));
        System.out.println(json);
        return json;
    }

    /*
    @GetMapping("")
    public String sessionGameStep(Model model) {

        model.addAttribute("score", userService.getAccountInfo().getScore());
        model.addAttribute("gameSessionSteps", sessionGameService.get15GameSessionStep(1));
        return "sessionGame";
    }

    @GetMapping("/verify")
    public String sessionGameConfirm(@ModelAttribute("response") LiteralTranslation literalTranslation) {
        sessionGameService.verifyResponse(literalTranslation);
        return "redirect:/sessionGame";
    }*/
}
