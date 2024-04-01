package com.example.geriadur.controller.api;

import com.example.geriadur.dto.GameSessionResult;
import com.example.geriadur.service.consultation.api.IWordStemService;
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
public class ProperNounsController {

    @Autowired
    private IWordStemService iWordStemService;
    @Autowired
    private IUserService userService;


    @GetMapping("/properNouns")
    public String getProperNounq() throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        String json = om.writeValueAsString(iWordStemService.getProperNouns());
        System.out.println(json);
        return json;
    }
}
