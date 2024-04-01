package com.example.geriadur.controller.api;

import com.example.geriadur.dto.ShowUser;
import com.example.geriadur.service.user.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
public class UserRestController {

    @Autowired
    private IUserService userService;

    @GetMapping("/users/{id}")
    public ShowUser showUser(@PathVariable(value = "id") Long id) {

        return userService.getUserById(id);
    }

}
