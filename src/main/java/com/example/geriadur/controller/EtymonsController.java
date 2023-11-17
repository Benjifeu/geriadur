package com.example.geriadur.controller;

import com.example.geriadur.domain.SemanticField;
import com.example.geriadur.service.EtymonService;
import com.example.geriadur.service.SemanticFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EtymonsController {
    @Autowired
    private EtymonService etymonService;

    //display list of semantic fields

    @GetMapping("/allEtymons")
    public String showWordsOfASemanticField( Model model) {
        model.addAttribute("etymons", etymonService.getAllEtymons());
        return "AllEtymons";
    }
}
