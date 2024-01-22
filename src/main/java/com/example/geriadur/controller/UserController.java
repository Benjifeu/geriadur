package com.example.geriadur.controller;

import com.example.geriadur.dto.CreateUser;
import com.example.geriadur.service.user.api.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("registration")
public class UserController {

    private IUserService userService;

    public UserController(IUserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public CreateUser userRegistrationDto(){
        return new CreateUser();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") CreateUser userRegistrationDto) {
        userService.save(userRegistrationDto);
        return "redirect:/registration?success";
    }
}
