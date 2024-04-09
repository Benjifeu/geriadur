package com.example.geriadur.controller.api;

import com.example.geriadur.constants.GenderEnum;
import com.example.geriadur.constants.LanguageEnum;
import com.example.geriadur.constants.WordClassEnum;
import com.example.geriadur.dto.ShowWordstem;
import com.example.geriadur.dto.ShowWordstemPage;
import com.example.geriadur.entity.consultation.WordStem;
import com.example.geriadur.service.consultation.api.ISemanticFieldService;
import com.example.geriadur.service.consultation.api.IWordStemService;
import com.example.geriadur.service.game.api.ISessionGameService;
import com.example.geriadur.service.user.api.IUserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController()
@Slf4j
public class WordStemRestController {

    @Autowired
    private IWordStemService wordStemService;
    @Autowired
    private ISemanticFieldService semanticFieldService;
    @Autowired
    private IUserService userService;

    @GetMapping("/wordstems")
    public ResponseEntity<List<ShowWordstem>> findAll() {
        log.info("The user with the email \"" + userService.getCurrentUserEmail()
                + "\" had retrieved all wordstems.");
        List<ShowWordstem> response = wordStemService.findAll();
        System.out.println(response);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @GetMapping("/wordstems/{id}")
    public String showWordStem(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("wordStem", wordStemService.getWordStemByID(id));
        return "wordStems/wordstems-Info";
    }

    @PostMapping("/wordstems/noun-image/{id}")
    public ResponseEntity<String> saveImage(@RequestBody MultipartFile image, @PathVariable("id") int properNounId) {
        wordStemService.saveImage(image, properNounId);
        return new ResponseEntity<>(
            "Image saved for proper noun with id ." + properNounId, 
            HttpStatus.OK);
    }

    @PutMapping("/wordstems/edit/{id}")
    public String editWordStem(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("wordStem", wordStemService.getWordStemByID(id));
        model.addAttribute("languages", LanguageEnum.values());
        model.addAttribute("wordClasses", WordClassEnum.values());
        model.addAttribute("genders", GenderEnum.values());
        model.addAttribute("semanticFields", semanticFieldService.getAllSemanticField());
        return "wordStems/wordstems-edit";
    }

    @PostMapping("/wordstems/save")
    public String saveWordStem(@ModelAttribute("wordStem") WordStem wordStem) {
        wordStemService.addWordStem(wordStem);
        return "redirect:/wordstems";
    }

    @DeleteMapping("/wordstems/{id}")
    public String deleteWordStem(@PathVariable(value = "id") Long id) {
        wordStemService.deleteWordStem(id);
        return "redirect:/wordstems";
    }

}
