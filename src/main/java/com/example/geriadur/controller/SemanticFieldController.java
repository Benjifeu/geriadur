package com.example.geriadur.controller;

import com.example.geriadur.domain.SemanticField;
import com.example.geriadur.service.SemanticFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SemanticFieldController {
    @Autowired
    private SemanticFieldService semanticFieldService;

    //display list of semantic fields
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listSemanticField", semanticFieldService.getAllSemanticField());
        return "SemanticFields";
    }

    @GetMapping("/newSemanticFieldForm")
    public String newSemanticFieldForm(Model model) {
        SemanticField semanticField = new SemanticField();
        model.addAttribute("semanticField", semanticField);
        return "new_semanticfield";
    }
    @PostMapping("/saveSemanticField")
    public String saveEmployee(@ModelAttribute("semanticField")SemanticField semanticField) {
        semanticFieldService.saveSematicField(semanticField);
        return "redirect:/";
    }
    @GetMapping("/semanticField/{id}")
    public String showWordsOfASemanticField(@PathVariable(value = "id")Long id, Model model) {
        model.addAttribute("etymons", semanticFieldService.getListOfEtymonsById(id));
        return "etymons";
    }
}
