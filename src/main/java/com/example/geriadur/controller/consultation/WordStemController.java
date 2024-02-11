package com.example.geriadur.controller.consultation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller()
public class WordStemController {



    @GetMapping("/wordstems/list")
    public String findPaginated() {
        return "wordStems/wordstems-show";
    }



}
