package com.example.geriadur.controller.api;

import com.example.geriadur.dto.GameSessionResult;
import com.example.geriadur.service.game.api.ISessionGameService;
import com.example.geriadur.service.user.api.IUserService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@Slf4j
public class SessionGameRestController {

    @Autowired
    private ISessionGameService ISessionGameService;
    @Autowired
    private IUserService userService;


    @GetMapping("/sessionGameData")
    public String getSessionGameData(@RequestParam Integer wordTheme) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        log.info("The user with the email \""+userService.getCurrentUserEmail()+"\" started a new game session with the theme "+wordTheme+".");
        String json = om.writeValueAsString(ISessionGameService.get15GameSessionStep(wordTheme));
        System.out.println(json);
        return json;
    }
    @PostMapping("/sessionGameData/saveResult")
    public ResponseEntity<String> saveScore(@RequestBody GameSessionResult gameSessionResult)  {
        ResponseEntity<String> response = userService.saveScore(gameSessionResult.getSessionScore(),gameSessionResult.getSessionTheme());
        return response;
    }
}
