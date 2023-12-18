package com.example.geriadur.controller.consultation;

import com.example.geriadur.constants.GenderEnum;
import com.example.geriadur.constants.LanguageEnum;
import com.example.geriadur.constants.WordClassEnum;
import com.example.geriadur.domain.consultation.Lexeme;
import com.example.geriadur.service.consultation.LexemeService;
import com.example.geriadur.service.consultation.SemanticFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LexemeController {
    @Autowired
    private LexemeService lexemeService;
    @Autowired
    private SemanticFieldService semanticFieldService;
    //private String "lexemes-show";

    //display list of semantic fields

    @GetMapping("lexemes")
    public String showLexemes(Model model) {
        return findPaginated(1, model);
    }

    @GetMapping("lexemes/{id}")
    public String showLexeme(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("lexeme", lexemeService.getLexemeByID(id));
        return "lexemes-Info";
    }

    @GetMapping("lexemes/add")
    public String addLexeme(Model model) {
        Lexeme lexeme = new Lexeme();
        model.addAttribute("lexeme", lexeme);
        model.addAttribute("languages", LanguageEnum.values());
        model.addAttribute("wordClasses", WordClassEnum.values());
        model.addAttribute("genders", GenderEnum.values());
        model.addAttribute("semanticFields", semanticFieldService.getAllSemanticField());
        return "lexemes-add";
    }

    @GetMapping("lexemes/edit/{id}")
    public String editLexeme(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("lexeme", lexemeService.getLexemeByID(id));
        model.addAttribute("languages", LanguageEnum.values());
        model.addAttribute("wordClasses", WordClassEnum.values());
        model.addAttribute("genders", GenderEnum.values());
        model.addAttribute("semanticFields", semanticFieldService.getAllSemanticField());
        return "lexemes-edit";
    }

    @PostMapping("lexemes/save")
    public String saveLexeme(@ModelAttribute("lexeme") Lexeme lexeme) {
        lexemeService.addLexeme(lexeme);
        return "redirect:/lexemes";
    }

    @GetMapping("lexemes/page/{pageNo}")
    public String findPaginated(@PathVariable("pageNo") int pageNo, Model model) {
        int pageSize = 5;
        Page<Lexeme> page = lexemeService.findPaginated(pageNo, pageSize);
        List<Lexeme> lexemes = page.getContent();;
        //lexemeService.getShowLexemes(List<Lexeme> lexemes);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("lexemes", lexemes);
        return "lexemes-show";
    }

    @GetMapping("lexemes/delete/{id}")
    public String deleteLexeme(@PathVariable(value = "id") Long id) {
        lexemeService.deleteLexeme(id);
        return "redirect:/lexemes";
    }

}
