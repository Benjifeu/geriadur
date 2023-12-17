package com.example.geriadur.controller;

import com.example.geriadur.constants.GenderEnum;
import com.example.geriadur.constants.LanguageEnum;
import com.example.geriadur.constants.WordClassEnum;
import com.example.geriadur.domain.Etymon;
import com.example.geriadur.service.EtymonService;
import com.example.geriadur.service.SemanticFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class EtymonsController {
    @Autowired
    private EtymonService etymonService;
    @Autowired
    private SemanticFieldService semanticFieldService;

    //display list of semantic fields

    @GetMapping("etymons")
    public String showEtymons(Model model) {
        return findPaginated(1, model);
    }

    @GetMapping("etymons/{id}")
    public String showEtymon(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("etymon", etymonService.getEtymonByID(id));
        return "etymons-Info";
    }

    @GetMapping("etymons/add")
    public String addEtymon(Model model) {
        Etymon etymon = new Etymon();
        model.addAttribute("etymon", etymon);
        model.addAttribute("languages", LanguageEnum.values());
        model.addAttribute("wordClasses", WordClassEnum.values());
        model.addAttribute("genders", GenderEnum.values());
        model.addAttribute("semanticFields", semanticFieldService.getAllSemanticField());
        return "etymons-add";
    }

    @GetMapping("etymons/edit/{id}")
    public String editEtymon(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("etymon", etymonService.getEtymonByID(id));
        model.addAttribute("languages", LanguageEnum.values());
        model.addAttribute("wordClasses", WordClassEnum.values());
        model.addAttribute("genders", GenderEnum.values());
        model.addAttribute("semanticFields", semanticFieldService.getAllSemanticField());
        return "etymons-edit";
    }

    @PostMapping("etymons/save")
    public String saveEtymon(@ModelAttribute("etymon") Etymon etymon) {
        etymonService.addEtymon(etymon);
        return "redirect:/etymons";
    }

    @GetMapping("etymons/page/{pageNo}")
    public String findPaginated(@PathVariable("pageNo") int pageNo, Model model) {
        int pageSize = 5;
        Page<Etymon> page = etymonService.findPaginated(pageNo, pageSize);
        List<Etymon> etymons = page.getContent();;
        //etymonService.getShowEtymons(List<Etymon> etymons);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("etymons", etymons);
        return "etymons";
    }

    @GetMapping("etymons/delete/{id}")
    public String deleteEtymon(@PathVariable(value = "id") Long id) {
        etymonService.deleteEtymon(id);
        return "redirect:/etymons";
    }

}
