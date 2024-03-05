package com.example.geriadur.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller()
public class WordStemController {



    @GetMapping("/wordstems/list")
    public String findPaginated() {
        return "wordstems-show";
    }



}
