package com.example.geriadur.controller.api;

import com.example.geriadur.constants.GenderEnum;
import com.example.geriadur.constants.LanguageEnum;
import com.example.geriadur.constants.WordClassEnum;
import com.example.geriadur.dto.ShowWordstem;
import com.example.geriadur.dto.ShowWordstemPage;
import com.example.geriadur.entity.consultation.WordStem;
import com.example.geriadur.service.consultation.api.ISemanticFieldService;
import com.example.geriadur.service.consultation.api.IWordStemService;
import com.example.geriadur.service.user.api.IUserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@Slf4j
public class UserRestController {
    @Autowired
    private IWordStemService wordStemService;
    @Autowired
    private ISemanticFieldService semanticFieldService;
    @Autowired
    private IUserService userService;

    @GetMapping("/4lastProperNouns/{pnum}/{psize}")
    public ShowWordstemPage findPaginated(@PathVariable("pnum") int pageNo, @PathVariable("psize") int pageSize) {
        log.info("The user with the email \"" + userService.getCurrentUserEmail()
                + "\" had retrieved a page from the wordstem table with " + pageSize + "words.");
        return wordStemService.findPaginated(pageNo, pageSize);
    }

    @GetMapping("/{id}")
    public String showWordStem(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("wordStem", wordStemService.getWordStemByID(id));
        return "wordStems/wordstems-Info";
    }

    @PostMapping("/save")
    public String saveWordStem(@ModelAttribute("wordStem") WordStem wordStem) {
        wordStemService.addWordStem(wordStem);
        return "redirect:/wordstems";
    }

    @GetMapping("/delete/{id}")
    public String deleteWordStem(@PathVariable(value = "id") Long id) {
        wordStemService.deleteWordStem(id);
        return "redirect:/wordstems";
    }

}
