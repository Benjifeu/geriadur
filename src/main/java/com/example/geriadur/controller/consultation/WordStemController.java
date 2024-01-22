package com.example.geriadur.controller.consultation;

import com.example.geriadur.constants.GenderEnum;
import com.example.geriadur.constants.LanguageEnum;
import com.example.geriadur.constants.WordClassEnum;
import com.example.geriadur.domain.consultation.WordStem;
import com.example.geriadur.service.consultation.api.ISemanticFieldService;
import com.example.geriadur.service.consultation.api.IWordStemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WordStemController {
    @Autowired
    private IWordStemService wordStemService;
    @Autowired
    private ISemanticFieldService semanticFieldService;
    //private String "wordStems-show";

    //display list of semantic fields

    @GetMapping("wordstems")
    public String showWordStems(Model model) {
        return findPaginated(1, model);
    }

    @GetMapping("wordstems/{id}")
    public String showWordStem(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("wordStem", wordStemService.getWordStemByID(id));
        return "wordStems/wordstems-Info";
    }

    @GetMapping("wordstems/add")
    public String addWordStem(Model model) {
        WordStem wordStem = new WordStem();
        model.addAttribute("wordStem", wordStem);
        model.addAttribute("languages", LanguageEnum.values());
        model.addAttribute("wordClasses", WordClassEnum.values());
        model.addAttribute("genders", GenderEnum.values());
        model.addAttribute("semanticFields", semanticFieldService.getAllSemanticField());
        return "wordStems/wordstems-add";
    }

    @GetMapping("wordStems/edit/{id}")
    public String editWordStem(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("wordStem", wordStemService.getWordStemByID(id));
        model.addAttribute("languages", LanguageEnum.values());
        model.addAttribute("wordClasses", WordClassEnum.values());
        model.addAttribute("genders", GenderEnum.values());
        model.addAttribute("semanticFields", semanticFieldService.getAllSemanticField());
        return "wordStems/wordstems-edit";
    }

    @PostMapping("wordstems/save")
    public String saveWordStem(@ModelAttribute("wordStem") WordStem wordStem) {
        wordStemService.addWordStem(wordStem);
        return "redirect:/wordstems";
    }

    @GetMapping("wordstems/page/{pageNo}")
    public String findPaginated(@PathVariable("pageNo") int pageNo, Model model) {
        int pageSize = 5;
        Page<WordStem> page = wordStemService.findPaginated(pageNo, pageSize);
        List<WordStem> wordStems = page.getContent();;
        //wordStemService.getShowWordStems(List<WordStem> wordStems);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("wordStems", wordStems);
        return "wordStems/wordstems-show";
    }

    @GetMapping("wordStems/delete/{id}")
    public String deleteWordStem(@PathVariable(value = "id") Long id) {
        wordStemService.deleteWordStem(id);
        return "redirect:/wordstems";
    }

}
