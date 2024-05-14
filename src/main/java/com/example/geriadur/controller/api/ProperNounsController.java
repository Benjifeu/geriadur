package com.example.geriadur.controller.api;

import com.example.geriadur.dto.ProperNounsDTO;
import com.example.geriadur.service.consultation.api.IWordStemService;
import com.example.geriadur.service.user.api.IUserService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@Slf4j
public class ProperNounsController {

    @Autowired
    private IWordStemService iWordStemService;
    @Autowired
    private IUserService userService;


    @GetMapping("/properNouns/")
    public ResponseEntity<List<ProperNounsDTO>> getProperNouns() throws JsonProcessingException {
        List<ProperNounsDTO> properNounDTO = iWordStemService.getProperNouns();
        System.out.println(properNounDTO);
        return new ResponseEntity<>(properNounDTO, HttpStatus.OK);
    }

    @PostMapping("/properNouns/")
    public ResponseEntity<String> saveWordStem(@RequestBody ProperNounsDTO properNounDTO) {
        iWordStemService.addProperNoun(properNounDTO);
        return new ResponseEntity<>(
            "The entity with the name " +properNounDTO.getCurrentName() +" has been saved", 
            HttpStatus.OK);
    }

}
