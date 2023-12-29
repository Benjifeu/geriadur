package com.example.geriadur.controller.api;

import com.example.geriadur.dto.GameSessionResult;
import com.example.geriadur.dto.GameSessionStep;
import com.example.geriadur.service.game.SessionGameService;
import com.example.geriadur.service.user.UserService;
import com.example.geriadur.service.user.UserServiceImpl;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SessionGameRestController {

    @Autowired
    private SessionGameService sessionGameService;
    @Autowired
    private UserService userService;


    @GetMapping("/sessionGame/get")
    public String getSessionGameData(@RequestParam Integer wordTheme) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        ;
        String json = om.writeValueAsString(sessionGameService.get15GameSessionStep(wordTheme));
        System.out.println(json);
        return json;
    }
    @PostMapping("/sessionGame/saveResult")
    public ResponseEntity saveScore(@RequestBody GameSessionResult gameSessionResult)  {
        ResponseEntity response = userService.saveScore(gameSessionResult.getSessionScore());
        return response;
    }
}
