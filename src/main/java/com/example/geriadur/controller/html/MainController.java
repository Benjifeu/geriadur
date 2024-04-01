package com.example.geriadur.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.geriadur.dto.CreateUser;
import com.example.geriadur.service.user.api.IUserService;

@Controller
public class MainController {

    private IUserService userService;

    public MainController(IUserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "redirect:/";
    }

    @GetMapping("")
    public String home() {
        return "home";
    }

    @ResponseBody
    @GetMapping("/admin")
    public String getUser(Model model) {
        return "Welcome admin";
    }

    @ResponseBody
    @GetMapping("/user")
    public String getAdmin(Model model) {
        return "Welcome user";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") CreateUser userRegistrationDto) {
        userService.save(userRegistrationDto);
        return "redirect:/registration?success";
    }
}
