package com.example.geriadur.controller.api;

import com.example.geriadur.constants.GenderEnum;
import com.example.geriadur.constants.LanguageEnum;
import com.example.geriadur.constants.TypeOfSourceEnum;
import com.example.geriadur.constants.WordClassEnum;
import com.example.geriadur.entity.consultation.Source;
import com.example.geriadur.dto.ShowSourcesPage;
import com.example.geriadur.dto.SourceBasicDTO;
import com.example.geriadur.service.consultation.api.ISourceService;
import com.example.geriadur.service.user.api.IUserService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController()
public class SourceController {
    @Autowired
    private ISourceService sourceService;
    @Autowired
    private IUserService userService;

    //display list of semantic fields
    @GetMapping("/sources")
    public ResponseEntity<List<SourceBasicDTO>> getSources()  {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        List<SourceBasicDTO> response = sourceService.getSourceStringList();
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
    


    @GetMapping("/sources/{id}")
    public String showSource(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("Source", sourceService.getSourceByID(id));
        return "sources/sources-Info";
    }

}
