package com.example.geriadur.controller.html;

import com.example.geriadur.entity.SemanticField;
import com.example.geriadur.service.consultation.api.ISemanticFieldService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("semanticfields")
public class SemanticFieldController {
    @Autowired
    private ISemanticFieldService semanticFieldService;

    //display list of semantic fields
    @GetMapping()
    public String viewHomePage(Model model) {
        model.addAttribute("listSemanticField", semanticFieldService.getAllSemanticField());
        return "semanticfields";
    }

    @GetMapping("/add")
    public String addSemanticField(Model model) {
        SemanticField semanticField = new SemanticField();
        model.addAttribute("semanticField", semanticField);
        return "semanticfields-add";
    }
    @PostMapping("/edit")
    public String saveSemanticField(@ModelAttribute("semanticField")SemanticField semanticField) {
        semanticFieldService.saveSematicField(semanticField);
        return "redirect:/";
    }
    @GetMapping("/{id}")
    public String showWordsOfASemanticField(@PathVariable(value = "id")Long id, Model model) {
        model.addAttribute("etymons", semanticFieldService.getListOfEtymonsBySemField(id));
        return "etymons";
    }
}
