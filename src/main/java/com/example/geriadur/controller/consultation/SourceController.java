package com.example.geriadur.controller.consultation;

import com.example.geriadur.constants.GenderEnum;
import com.example.geriadur.constants.LanguageEnum;
import com.example.geriadur.constants.TypeOfSourceEnum;
import com.example.geriadur.constants.WordClassEnum;
import com.example.geriadur.domain.consultation.Source;
import com.example.geriadur.service.consultation.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("sources")
public class SourceController {
    @Autowired
    private SourceService sourceService;


    //display list of semantic fields

    @GetMapping()
    public String showallSources(Model model) {
        return findPaginated(1, model);
    }

    @GetMapping("/{id}")
    public String showSource(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("Source", sourceService.getSourceByID(id));
        return "sources/sources-Info";
    }

    @GetMapping("/add")
    public String addSource(Model model) {
        Source Source = new Source();
        model.addAttribute("source", Source);
        model.addAttribute("languages", LanguageEnum.values());
        model.addAttribute("typeOfSources", TypeOfSourceEnum.values());
        return "sources/sources-add";
    }

    @GetMapping("/edit/{id}")
    public String editSource(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("Source", sourceService.getSourceByID(id));
        model.addAttribute("languages", LanguageEnum.values());
        model.addAttribute("wordClasses", WordClassEnum.values());
        model.addAttribute("genders", GenderEnum.values());
        return "sources/sources-edit";
    }

    @PostMapping("/save")
    public String saveSource(@ModelAttribute("Source") Source Source) {
        sourceService.addSource(Source);
        return "redirect:/Sources";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable("pageNo") int pageNo, Model model) {
        int pageSize = 5;
        Page<Source> page = sourceService.findPaginated(pageNo, pageSize);
        List<Source> Sources = page.getContent();
        System.out.println(Sources);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("Sources", Sources);
        return "sources/sources";
    }

    @GetMapping("/delete/{id}")
    public String deleteSource(@PathVariable(value = "id") Long id) {
        sourceService.deleteSource(id);
        return "redirect:/Sources";
    }

}
