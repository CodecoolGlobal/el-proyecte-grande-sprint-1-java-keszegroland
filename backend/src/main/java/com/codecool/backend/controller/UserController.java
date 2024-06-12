package com.codecool.backend.controller;

import com.codecool.backend.controller.dto.NewUserDTO;
import com.codecool.backend.dao.model.User;
import com.codecool.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users/{id}")
    public User getUser(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PostMapping("api/users")
    public boolean signUp(@RequestBody NewUserDTO user) {
        return userService.createNewUser(user);
    }
}
