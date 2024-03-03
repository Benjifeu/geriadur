package com.example.geriadur.controller.api;

import com.example.geriadur.constants.GenderEnum;
import com.example.geriadur.constants.LanguageEnum;
import com.example.geriadur.constants.WordClassEnum;
import com.example.geriadur.dto.CreateUser;
import com.example.geriadur.dto.ShowUser;
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
    private IUserService userService;

    @GetMapping("/users/{id}")
    public ShowUser showUser(@PathVariable(value = "id") Long id) {

        return userService.getUserById(id);
    }

}
