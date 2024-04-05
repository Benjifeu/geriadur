package com.example.geriadur.controller.api;

import com.example.geriadur.constants.GenderEnum;
import com.example.geriadur.constants.LanguageEnum;
import com.example.geriadur.constants.TypeOfSourceEnum;
import com.example.geriadur.constants.WordClassEnum;
import com.example.geriadur.entity.consultation.Source;
import com.example.geriadur.dto.ShowSourcesPage;
import com.example.geriadur.service.consultation.api.ISourceService;
import com.example.geriadur.service.user.api.IUserService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/sources/{pnum}/{psize}")
    public ShowSourcesPage findPaginated(@PathVariable("pnum") int pageNo, @PathVariable("psize") int pageSize) {
        log.info("The user with the email \"" + userService.getCurrentUserEmail()
                + "\" had retrieved a page from the wordstem table with " + pageSize + "words.");
        return sourceService.findPaginated(pageNo, pageSize);
    }
    


    @GetMapping("/sources/{id}")
    public String showSource(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("Source", sourceService.getSourceByID(id));
        return "sources/sources-Info";
    }

    @PostMapping("/sources/add")
    public String addSource(Model model) {
        Source Source = new Source();
        model.addAttribute("source", Source);
        model.addAttribute("languages", LanguageEnum.values());
        model.addAttribute("typeOfSources", TypeOfSourceEnum.values());
        return "sources/sources-add";
    }

    @PutMapping("/sources/edit/{id}")
    public String editSource(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("Source", sourceService.getSourceByID(id));
        model.addAttribute("languages", LanguageEnum.values());
        model.addAttribute("wordClasses", WordClassEnum.values());
        model.addAttribute("genders", GenderEnum.values());
        return "sources/sources-edit";
    }

    @DeleteMapping("/sources/delete/{id}")
    public String deleteSource(@PathVariable(value = "id") Long id) {
        sourceService.deleteSource(id);
        return "redirect:/Sources";
    }

}
